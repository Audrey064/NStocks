package com.example.nstocks.services;

import com.example.nstocks.entities.Article;
import com.example.nstocks.entities.MenuDTO;
import com.example.nstocks.entities.dtos.ArticleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleServices
{
   public String create_Article (ArticleDTO article);

   public List <Article> getAllArticles();

   public Article update_article(ArticleDTO article);

   public void delete_article(Article article);

   public Article get_article_byRef(String ref);

   public String delete_article_byRef(String ref);

   public Article search_article_byName(String name);

   public List <String> getAllCategories();

   public int nb_ItemCategory (String Category);

   public List <Article> getAllArticleRangingPrice (double price1, double price2);

  public List <Article> search_articles_byCategory(String category) ;

  public List <MenuDTO> displayMenu () ;
}
