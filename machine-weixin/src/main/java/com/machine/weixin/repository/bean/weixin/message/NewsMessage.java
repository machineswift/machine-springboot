package com.machine.weixin.repository.bean.weixin.message;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 图文消息, 点击跳转到外链
 */
@Data
public class NewsMessage extends CustomMessage {
    private News news;

    public NewsMessage() {
        setMsgtype("news");
    }

    public void addArticle(String title, String description, String url, String picurl) {
        if (Objects.isNull(news)) {
            this.news = new News();
        }
        news.addArticle(title, description, url, picurl);
    }

    @Data
    @NoArgsConstructor
    static class News {
        private List<Article> articles;

        public void addArticle(String title, String description, String url, String picurl) {
            if (Objects.isNull(articles)) {
                articles = new ArrayList<>();
            }
            articles.add(new Article(title, description, url, picurl));
        }
    }

    @Data
    @NoArgsConstructor
    static class Article {
        private String title;
        private String description;
        private String url;
        private String picurl;

        public Article(String title,
                       String description,
                       String url,
                       String picurl) {
            this.title = title;
            this.description = description;
            this.url = url;
            this.picurl = picurl;
        }
    }
}
