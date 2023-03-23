package com.example.nstocks.controllers;

import com.example.nstocks.entities.Article;
import com.example.nstocks.entities.MenuDTO;
import com.example.nstocks.entities.dtos.ArticleDTO;
import com.example.nstocks.services.ArticleServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/articles")
@Slf4j
public class ArticlesController
{
    @Autowired
    private ArticleServices articleServices ;

    @GetMapping("/GetAllArticles")
    public List <Article> getAllArticles()
    {
        return articleServices.getAllArticles();
    }

    @GetMapping("/GetAllCategories")
    public List <String> getAllCategories()
    {
        var categories = articleServices.getAllCategories();
        log.info("the list of all categories : {}",categories);
        return categories;
    }

  @GetMapping("/GetArticlesByReference/{ref}")
    public Article get_articles_by_reference(@PathVariable String ref)
    {
      // try {
           return articleServices.get_article_byRef(ref);
      // } catch (Exception e)
        // {
         //  System.out.println("There is no article with  this reference");
      // }

    }

    @GetMapping ("/GetArticlesByName/{name}")
    public Article search_articles_byName(@PathVariable String name)
    {
        return articleServices.search_article_byName(name);
    }

    @GetMapping ("/SearchArticlesByCategory/{category}")
    public List <Article> search_articles_ByCategory (@PathVariable String category)
    {
        return articleServices.search_articles_byCategory(category);
    }


    @GetMapping("/SearchArticlesByRangingPrice/{price1}&{price2}")
    public List <Article> search_articles_ByRangingPrice(@PathVariable double price1, @PathVariable double price2)
    {
       return  articleServices.getAllArticleRangingPrice(price1,price2) ;
    }

    @GetMapping("/DeleteArticleByReference/{ref}")
    public void delete_article_BYRef(@PathVariable String ref)
    {
        articleServices.delete_article_byRef(ref);
    }

    @GetMapping ("/NumberArticles_Category/{category}")
    public int numberItemsCategory(@PathVariable String category)
    {
       return articleServices.nb_ItemCategory(category);
    }

  /*  @GetMapping(path = "/Menu")
    public String displayMenu()
    {
        return "acceuil";
    }*/

    @GetMapping ("/Menu")
    public List <MenuDTO> displayMenu ()
    {
        return articleServices.displayMenu();
    }

    @PostMapping("/CreateArticle")
    public String createArticle(@RequestBody ArticleDTO article)
    {
         return articleServices.create_Article(article);
    }

    @PutMapping("/UpdateArticle")
    public Article updateArticle(@RequestBody ArticleDTO article)
    {
        return articleServices.update_article(article);
    }

    @DeleteMapping("/DeleteArticleByRef/{ref}")
    public String deleteArticleByReference(@PathVariable String ref)
    {
        return articleServices.delete_article_byRef(ref);
    }
}
