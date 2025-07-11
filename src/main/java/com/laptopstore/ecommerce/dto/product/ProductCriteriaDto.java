package com.laptopstore.ecommerce.dto.product;

import com.laptopstore.ecommerce.dto.PageableCriteriaDto;

import com.laptopstore.ecommerce.util.constant.StockStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class ProductCriteriaDto extends PageableCriteriaDto {

    public int getFilterCount() {
        int count = 0;

        if (categoryNames != null && !categoryNames.isEmpty()) count++;
        if (brandNames != null && !brandNames.isEmpty()) count++;
        if (getDoubleMinPrice() != null && getDoubleMaxPrice() != null && getDoubleMinPrice() <= getDoubleMaxPrice())
            count++;
        if (getDoubleMinDiscountPrice() != null && getDoubleMaxDiscountPrice() != null && getDoubleMinDiscountPrice() <= getDoubleMaxDiscountPrice())
            count++;
        if (getEnumStockStatus() != null) count++;

        return count;
    }

    public String toQuery() {
        StringBuilder stringBuilder = new StringBuilder();

        if (name != null && !name.isEmpty()) {
            stringBuilder.append("&name=").append(name);
        }

        if (categoryNames != null && !categoryNames.isEmpty()) {
            for (String category : categoryNames) {
                stringBuilder.append("&categoryNames=").append(category);
            }
        }

        if (brandNames != null && !brandNames.isEmpty()) {
            for (String brand : brandNames) {
                stringBuilder.append("&brandNames=").append(brand);
            }
        }

        if(getInstantMonthLimit() != null && getInstantMonthLimit().isBefore(Instant.now())){
            stringBuilder.append("&monthLimit=").append(monthLimit);
        }

        if (getDoubleMinPrice() != null && getDoubleMinPrice() > 0) {
            stringBuilder.append("&minPrice=").append(minPrice);
        }

        if (getDoubleMaxPrice() != null && getDoubleMaxPrice() > 0) {
            stringBuilder.append("&maxPrice=").append(maxPrice);
        }

        if (getDoubleMinDiscountPrice() != null && getDoubleMinDiscountPrice() > 0) {
            stringBuilder.append("&minDiscountPrice=").append(minDiscountPrice);
        }

        if (getDoubleMaxDiscountPrice() != null && getDoubleMaxDiscountPrice() > 0) {
            stringBuilder.append("&maxDiscountPrice=").append(maxDiscountPrice);
        }

        if (getEnumStockStatus() != null) {
            stringBuilder.append("&stockStatus=").append(stockStatus);
        }

        if (getIntegerPage() != null && getIntegerPage() > 0) {
            stringBuilder.append("&page=").append(getPage());
        }

        if (getIntegerLimit() != null && getIntegerLimit() > 0) {
            stringBuilder.append("&limit=").append(getLimit());
        }

        if (getSortBy() != null && !getSortBy().isEmpty()) {
            stringBuilder.append("&sortBy=").append(getSortBy());
        }

        if (getEnumSortDirection() != null) {
            stringBuilder.append("&sortDirection=").append(getSortDirection());
        }

        if (!stringBuilder.isEmpty()) {
            stringBuilder.setCharAt(0, '?');
        }

        return stringBuilder.toString();
    }

    public Double getDoubleMinPrice() {
        try {
            return Double.parseDouble(minPrice);
        } catch (Exception e) {
            return null;
        }
    }

    public Double getDoubleMaxPrice() {
        try {
            return Double.parseDouble(maxPrice);
        } catch (Exception e) {
            return null;
        }
    }

    public Double getDoubleMinDiscountPrice() {
        try {
            return Double.parseDouble(minDiscountPrice);
        } catch (Exception e) {
            return null;
        }
    }

    public Double getDoubleMaxDiscountPrice() {
        try {
            return Double.parseDouble(maxDiscountPrice);
        } catch (Exception e) {
            return null;
        }
    }

    public StockStatusEnum getEnumStockStatus() {
        try {
            return StockStatusEnum.valueOf(stockStatus.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

    public Instant getInstantMonthLimit() {
        try {
            int month = Integer.parseInt(monthLimit);
            return ZonedDateTime.now().minusMonths(month).toInstant();
        } catch (Exception e) {
            return ZonedDateTime.now().minusMonths(3).toInstant();
        }
    }

    private String monthLimit = "1";
    private String name;
    private List<String> categoryNames;
    private List<String> brandNames;
    private String minPrice;
    private String maxPrice;
    private String minDiscountPrice;
    private String maxDiscountPrice;
    private String stockStatus;
}