package com.blue.mvpdemo.news.model;

import com.blue.mvpdemo.beans.NewsBean;
import com.blue.mvpdemo.beans.NewsDetailBean;
import com.blue.mvpdemo.net.Urls;
import com.blue.mvpdemo.news.NewsJsonUtils;
import com.blue.mvpdemo.news.widget.NewsFragment;
import com.blue.mvpdemo.utils.OkHttpUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/5/10.
 */
public class NewsModelImpl implements NewsModel{

    NewsFragment newsFragment;

    /**
     * 加载新闻列表
     * @param url
     * @param type
     * @param listener
     */
    @Override
    public void loadNews(String url, final int type, final OnLoadNewsListListener listener) {
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<NewsBean> newsBeanList = NewsJsonUtils.readJsonNewsBeans(response,getID(type));
                listener.onSuccess(newsBeanList);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("联网失败",e);
            }
        };
        OkHttpUtils.get(url,loadNewsCallback);
    }

    @Override
    public void loadNewsDetail(final String docid, final OnLoadNewsDetailListener listener) {
        String url = getDetailUrl(docid);
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                NewsDetailBean newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response, docid);
                listener.onSuccess(newsDetailBean);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news detail info failure.", e);
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);
    }


    private String getID(int type){
        String id;
        switch (type){
            case NewsFragment.NEWS_TYPE_TOP:
                id = Urls.TOP_ID;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                id = Urls.NBA_ID;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                id = Urls.CAR_ID;
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                id = Urls.JOKE_ID;
                break;
            default:
                id = Urls.TOP_ID;
                break;
        }

        return id;
    }
    //把url拼接后以供调用
    private String getDetailUrl(String docID){
        StringBuffer sb = new StringBuffer(Urls.NEW_DETAIL);
        sb.append(docID).append(Urls.END_DETAIL_URL);
        return sb.toString();
    }

    public interface OnLoadNewsListListener{
        void onSuccess(List<NewsBean> list);
        void onFailure(String msg,Exception e);
    }

    public interface OnLoadNewsDetailListener{
        void onSuccess(NewsDetailBean newsDetailBean);
        void onFailure(String msg,Exception e);
    }
}
