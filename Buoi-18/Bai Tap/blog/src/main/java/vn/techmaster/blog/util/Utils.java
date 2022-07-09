package vn.techmaster.blog.util;

import vn.techmaster.blog.dto.BlogDto;
import vn.techmaster.blog.entity.Category;

import java.util.List;

public class Utils {
    public static String generateCategoryString(List<BlogDto.CategoryDto> categories){
        if (categories.size() == 0){
            return "";
        }
        //Lay list name cua Category
        List<String> categoriesName = categories.stream().map(BlogDto.CategoryDto::getName).toList();

        // Chuyen List -> Array
        String[] itemArray  = new String[categoriesName.size()];
        itemArray = categoriesName.toArray(itemArray);

        // Join array -> String
        return String.join(", ", itemArray);
    }
}
