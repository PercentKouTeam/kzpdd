package com.hpercent.snail.app.factorys;

/**
 * Created by koudejian on 14-7-29.
 *
 */
public class Json2List {
    /*
    public static LinkedList<CommentsModel> getCommentsList(String jsonData, LinkedList<CommentsModel> result){
        if(result == null ){
            result = new LinkedList<CommentsModel>();
        }
        if(result.size() == 0){//第一条数据占位，被app详情顶替
            result.add(new CommentsModel("0", "0000-00-00 00:00:00", "null"));
        }
        if(jsonData != null){
            if(!"".equals(jsonData)){
                try {
                    JSONArray arr = new JSONArray(jsonData);
                    int length = arr.length();
                    for(int i = 0; i < length; i++){
                        JSONObject temp = (JSONObject) arr.get(i);

                        String id = temp.getString(JsonConfig.KEY_COMMENTS_ID);
                        String times = temp.getString(JsonConfig.KEY_COMMENTS_TIME);
                        String comments = temp.getString(JsonConfig.KEY_COMMENTS_CONTENT);

                        result.add(new CommentsModel(id, times, comments));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    //*/
}
