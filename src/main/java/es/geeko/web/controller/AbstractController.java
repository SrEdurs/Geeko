package es.geeko.web.controller;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AbstractController<DTO> {
    //Literal para los numeros de página
    protected final String pageNumbersAttributeKey = "pageNumbers";

    //Metodo para obtener los numeros de página
    protected List<Integer> dameNumPaginas(Page<DTO>  dtos){
        List<Integer> pageNumbers = new ArrayList<>();
        int totalPages = dtos.getTotalPages();

        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
        }
        return pageNumbers;
    }
}

