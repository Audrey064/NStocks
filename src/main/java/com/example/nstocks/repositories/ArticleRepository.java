package com.example.nstocks.repositories;

import com.example.nstocks.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository <Article, String>
{
    Optional<Article> findByName(@Param("name") String name);

    List <Article> findArticlesByCategory(@Param("category") String category) ;

    List <Article>  findArticlesByPriceBetween(double price1, double price2);
    Article findArticlesByPrice (@Param ("price") double price) ;
}
