package com.backend.services.servicesImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.backend.dto.DiscountDTO;
import com.backend.model.DetailsProduct;
import com.backend.model.Discount;
import com.backend.repository.DiscountRepository;
import com.backend.services.DiscountService;
import com.backend.util.RandomUtil;

import jakarta.mail.internet.ParseException;

@Service
public class DiscountImpl implements DiscountService{

	@Autowired
	private DiscountRepository discountRepository;

	@Override
	public List<Discount> getAllDiscount() {
		return discountRepository.findAll();
	}
	
	private List<DiscountDTO> convertToProductsDTO(List<Object[]> results) {
		List<DiscountDTO> DiscountDTOList = new ArrayList<>();

		for (Object[] result : results) {
			DiscountDTO DiscountDTO = new DiscountDTO();
			DiscountDTO.setId((String) result[0]); 

			DiscountDTOList.add(DiscountDTO);
		}

		return DiscountDTOList;
	}

	public List<DiscountDTO> getProductDt(String sizeId, String colorId) {
		List<Object[]> results = discountRepository.getProductDt(sizeId,colorId);
		return convertToProductsDTO(results);
	}
	SimpleDateFormat sdfWithTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH);
    SimpleDateFormat sdfWithoutTime = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

	@Override
	public Object getAllDiscount(int page, int size) {
		return discountRepository.findAllDiscount(PageRequest.of(page, size));
	}

    @Override
    public Object updateDiscount(String id, Double discount, String startDate, String endDate, String productId) throws
                                                                                                                 ParseException {
        Date date1 = null;
        Date date2 = null;
        if (startDate.contains(":")) { // Ki?m tra xem c� gi? trong chu?i kh�ng
            try {
				date1 = sdfWithTime.parse(startDate);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
            try {
				date1 = sdfWithoutTime.parse(startDate);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        if (endDate.contains(":")) { // Ki?m tra xem c� gi? trong chu?i kh�ng
            try {
				date2 = sdfWithTime.parse(endDate);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
            try {
				date2 = sdfWithoutTime.parse(endDate);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        var discountUpdate = discountRepository.findById(id);
        Date finalDate = date1;
        Date finalDate1 = date2;
        discountUpdate.ifPresentOrElse(discount1 -> {
            discount1.setPrice_sale(discount);
            discount1.setDate_start(finalDate);
            discount1.setDate_end(finalDate1);
            var detailsProduct = new DetailsProduct();
            detailsProduct.setId(productId);
            discount1.setDetailsProduct(detailsProduct);
            discountRepository.save(discount1);
        }, () -> {
            var lastDiscount = discountRepository.findLastDiscount();
            var newDiscount = new Discount();
            if (lastDiscount.isPresent()) {
                newDiscount.setId(RandomUtil.getNextId(lastDiscount
                                                               .get()
                                                               .getId(), "DI"));
            } else {
                newDiscount.setId(RandomUtil.getNextId(null, "DI"));
            }
            newDiscount.setPrice_sale(discount);
            newDiscount.setDate_start(finalDate);
            newDiscount.setDate_end(finalDate1);
            var detailsProduct = new DetailsProduct();
            detailsProduct.setId(productId);
            newDiscount.setDetailsProduct(detailsProduct);
            discountRepository.save(newDiscount);
        });
        return "OK";
    }

    @Override
    public Object deleteDiscount(String id) {
        discountRepository.deleteById(id);
        return "OK";
    }

    @Override
    public Object searchDiscount(String searchDateFrom, String searchDateTo) {
        Date date1 = null;
        Date date2 = null;
        try {
			date1 = sdfWithoutTime.parse(searchDateFrom);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			date2 = sdfWithoutTime.parse(searchDateTo);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return discountRepository.searchDiscountFromTo(
                new java.sql.Date(date1.getTime()), new java.sql.Date(date2.getTime()), PageRequest.of(0, 10));
    }
}
