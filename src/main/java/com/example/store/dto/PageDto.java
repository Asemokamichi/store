package com.example.store.dto;

import com.example.store.entity.Product;
import com.example.store.service.ProductService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageDto <T>{

    private Integer currentPage;
    private Integer totalPage;
    private List<T> list;


    public PageDto (Page<T> objsPage){
        List<T> objs = objsPage.getContent();

        currentPage = objsPage.getNumber();
        totalPage = objsPage.getTotalPages();
        list = objs;

    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
