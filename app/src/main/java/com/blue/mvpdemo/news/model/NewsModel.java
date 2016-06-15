package com.blue.mvpdemo.news.model;

/**
 * Created by Administrator on 2016/5/10.
 */
public interface NewsModel {
    void loadNews(String url,int type,NewsModelImpl.OnLoadNewsListListener listener);
    void loadNewsDetail(String docid, NewsModelImpl.OnLoadNewsDetailListener listener);

}
