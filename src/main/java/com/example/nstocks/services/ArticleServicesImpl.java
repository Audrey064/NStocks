package com.example.nstocks.services;

import com.example.nstocks.entities.Article;
import com.example.nstocks.entities.MenuDTO;
import com.example.nstocks.entities.dtos.ArticleDTO;
import com.example.nstocks.repositories.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ArticleServicesImpl implements ArticleServices
{
    private final ArticleRepository articlesRepository;

    public ArticleServicesImpl(ArticleRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }

    @Override
    public String create_Article(ArticleDTO article)
    {
         if (articlesRepository.findById(article.getRef_id()).isEmpty())
           {
               Article newArticle = new Article(article.getRef_id(), article.getName(), article.getCategory(), article.getPrice());
               articlesRepository.save(newArticle);
               return "The article has been created";
           }
           else {
               return "An article with this reference already exists";
           }
     /*   log.info("article data is {}", article);
       /* Optional <Article> up_article = articlesRepository.findById(article.getRef_id());
        if (up_article.isEmpty())
        {
            articlesRepository.save(article);
            up_article.get();
        }

        else
        {
            System.out.println("This article already exist in our stock");
        } */
     /*  Article newArticle = new Article(article.getRef_id(), article.getName(), article.getCategory(), article.getPrice());
      // newArticle.setRef_id(article.getRef_id());
       articlesRepository.save(newArticle);

      return "The article has been created";*/
     }

    @Override
    public List<Article> getAllArticles()
    {
        var articles = articlesRepository.findAll();
        return articles;
    }

    @Override
    public Article update_article(ArticleDTO article)
    {
        /*
        List <Article> allArticles = articlesRepository.findAll();
        Article upArticle = new Article();
        int j = 0;
        boolean notFound = true ;
        for (int i = 0; i < allArticles.size() ; i++)
        {
            if (Objects.equals(allArticles.get(i).getRef_id(), ref))
            {
                notFound = false ;
                j =i ;
                break ;
            }
        }

        if (notFound == false)
        {
           articlesRepository.save(upArticle);
        }
        /*
        Optional <Articles> up_article = articlesRepository.findById(article.getRef_id());
        if (up_article.isEmpty())
        {
            System.out.println("This article doesn't article in our stock");
            return null ;
        }
       else
       {
              return articlesRepository.save(article);
       }
*/
        var refArticle = articlesRepository.findById(article.getRef_id());
        Article upArticle = new Article(article.getRef_id(), article.getName(), article.getCategory(), article.getPrice());
        if (refArticle.isEmpty()) {
            return null;
        }
        else {
            return articlesRepository.save(upArticle);
        }
    }
    @Override
    public void delete_article(Article article)
    {
        Optional <Article> up_article = articlesRepository.findById(article.getRef_id());
        if (up_article.isEmpty())
        {
            System.out.println("This article doesn't article in our stock");
        }
        else
        {
            articlesRepository.delete(article);
        }
    }

    @Override
    public Article get_article_byRef(String ref)
    {
       // List <Articles> allArticles = articlesRepository.findAll();
        /* int j;
        boolean notFound = true ;
        for (int i = 0 ; i < allArticles.size(); i++)
        {
            if (Objects.equals(ref, allArticles.get(i).getRef_id()))
            {
                notFound = false;
                j = i ;
                break;
            }
        }
        if (notFound == false)
        {
            System.out.println("The item with this reference is available in our stock");

        }
        else
        {
            System.out.println("The item with this reference is not available in our stock");
        }
         */
        Article n_articles =  articlesRepository.findById(ref).orElse(null);
        /*if (n_articles.isEmpty())
        {
            System.out.println("The item with this reference isn't available in our stock");

        }
        else
        {
            System.out.println("The item with this reference is available in our stock");
        }
*/
        return n_articles ;
    }

    @Override
    public String delete_article_byRef(String ref)
    {
        /*
        List <Article> allArticles = articlesRepository.findAll();
        boolean notFound = true ;
       for (int i = 0; i < allArticles.size(); i++)
       {
           if (Objects.equals(allArticles.get(i).getRef_id(), ref))
           {
               notFound =  false;
               break ;
           }
       }
       if (notFound == false)
       {
           articlesRepository.deleteById(ref);
       }
         */
        var refArticle = articlesRepository.findById(ref);
        if (refArticle.isEmpty()) {
            articlesRepository.deleteById(ref);
            return "The article has been deleted";
        }
        else {
            return "We don't have this item";
        }
    }

    @Override
    public Article search_article_byName(String name)
    {
        Article n_articles =  articlesRepository.findByName(name).orElse(null);
        /*if (n_articles.isEmpty())
        {
            System.out.println("The item with this name isn't available in our stock");
        }
        else
        {
            System.out.println("The item with this name is available in our stock");
        }  */
        return n_articles ;
    }

    @Override
    public List <String> getAllCategories()
    {
        List <Article> allArticles = articlesRepository.findAll();
        List <String> allCategories = new ArrayList<>();
        String instCategory ;
       for (int i = 0 ; i < allArticles.size(); i++)
       {
           instCategory = allArticles.get(i).getCategory();
           allCategories.add(i,instCategory);
       }
        return allCategories;
    }

    @Override
    public int nb_ItemCategory(@RequestBody String category)
    {
        int nbItem = 0;
        List <Article> allArticles = articlesRepository.findAll();
        for (int i = 0 ; i < allArticles.size() ; i++)
        {
            if (Objects.equals(allArticles.get(i).getCategory(), category))
            {
                nbItem++;
            }
        }
        System.out.println("There is/are " + nbItem + " article(s) in this category ");
        return nbItem ;
    }

    @Override
    public List<Article> getAllArticleRangingPrice(double price1, double price2)
    {
       List <Article> pr_articles = articlesRepository.findArticlesByPriceBetween(price1,price2);

       /* List <Articles> allArticles = articlesRepository.findAll();
        List <Articles> artPrices = new ArrayList<>();
        boolean notFound = true ;
        for (int i = 0; i < allArticles.size(); i++)
        {
            if ((allArticles.get(i).getPrice() <= price1 && allArticles.get(i).getPrice() >= price2) || (allArticles.get(i).getPrice() <= price2 && allArticles.get(i).getPrice() >= price1) )
            {
                notFound = false;
                artPrices.add(i, allArticles.get(i));
            }

        }

        if (notFound == false)
        {
            return artPrices ;
        }
        else
        {
            System.out.println("We have no products in this price range");
        }
        return artPrices ; */
             return pr_articles ;
    }

    @Override
    public List <Article> search_articles_byCategory(String category)
    {
        List<Article> n_articles =  articlesRepository.findArticlesByCategory(category);
        List <Article> art_cat = new ArrayList<>();
        if (n_articles.isEmpty())
        {
            System.out.println(" Articles of the category " + category + " in our stock");
           //art_cat = n_articles ;
        }
        else
        {
            System.out.println("We doesn't have any this category in our stock");
        }
      return (List<Article>) n_articles ;
    }

 @Override
    public List<MenuDTO> displayMenu()
    {
        List <MenuDTO> menuDTOS = new ArrayList<>();
 
        MenuDTO link1 = new MenuDTO(1,"Display the list of articles : localhost:1101/articles/GetAllArticles" );

      //  link1.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        menuDTOS.add(link1);
        MenuDTO link2 = new MenuDTO(2,"Show all article categories : localhost:1101/articles/GetAllCategories" ) ;
        menuDTOS.add(link2);
        MenuDTO link3 = new MenuDTO(3,"Search an article by its reference : localhost:1101/articles/GetArticlesByReference/{ref}" ) ;
        menuDTOS.add(link3);
        MenuDTO link4 = new MenuDTO(4,"Search an article by its name: localhost:1101/GetArticlesByName/{name}" ) ;
        menuDTOS.add(link4);
        MenuDTO link5 = new MenuDTO(5,"Display the list of articles in a category : localhost:1101/articles/SearchArticlesByCategory/{category}" ) ;
        menuDTOS.add(link5);
        MenuDTO link6 = new MenuDTO(6,"The number of articles in a category : localhost:1101/articles/NumberArticles_Category/{category}" ) ;
        menuDTOS.add(link6);
        MenuDTO link7 = new MenuDTO(7,"Delete an article using its reference : localhost:1101/articles/DeleteArticleByReference/{ref}" ) ;
        menuDTOS.add(link7);
        MenuDTO link8 = new MenuDTO(8,"Display the list of items in a price range  : localhost:1101/articles/SearchArticlesByRangingPrice/{price1}&{price2}" ) ;
        menuDTOS.add(link8);
     //  System.out.println(menuDTOS);
        MenuDTO link9 = new MenuDTO(9,"Create an item : localhost:1101/articles/CreateArticle");
        menuDTOS.add(link9);
        MenuDTO link10 = new MenuDTO(10,"Update an item : localhost:1101/articles/UpdateArticle");
        menuDTOS.add(link10);
        MenuDTO link11 = new MenuDTO(11,"Delete Article by reference (Using DeleteMapping) : localhost:1101/articles//DeleteArticleByRef/{ref}");
        menuDTOS.add(link11);
        return menuDTOS ;
    }
}
