class user
{
    HashMap<Integer,Integer> tweet = new HashMap();
    Set<Integer> followee= new HashSet();  
    
}
class Twitter {
int timestamp=1;
    HashMap<Integer,user> uniqueUser=null;
    public Twitter() {
        uniqueUser= new HashMap();  
        
    }
    
    public void postTweet(int userId, int tweetId) {
        
      user user = uniqueUser.get(userId);
    if (user != null) {
      user.tweet.put(timestamp, tweetId);
    } else {
      user newUser = new user();
      newUser.tweet.put(timestamp, tweetId);
      uniqueUser.put(userId, newUser);
    }
   
        
        
         timestamp++;
        
        
        
        
    }
    
    public List<Integer> getNewsFeed(int userId) {
         ArrayList<Map.Entry<Integer,Integer>> result = new ArrayList();
    user user = uniqueUser.get(userId);
    if (user != null) {
      result.addAll(user.tweet.entrySet());
      for (int i: user.followee) {
        user user2 = uniqueUser.get(i);
        if (user2 != null) {
          result.addAll(user2.tweet.entrySet());
        }
      }
      Collections.sort(result, (Map.Entry<Integer,Integer> a, Map.Entry<Integer,Integer> b ) ->  b.getKey()-a.getKey());
    }
    List<Integer> list = new ArrayList();
    for(Map.Entry<Integer,Integer> entry : result){
        list.add(entry.getValue());
        if(list.size()==10)
            return list;
        
    }
    return list;
        
        
    }
    
    public void follow(int followerId, int followeeId) {
        
        user user = uniqueUser.get(followerId);
    if (user != null) {
      user.followee.add(followeeId);
    } else {
      user newUser = new user();
      newUser.followee.add(followeeId);
      uniqueUser.put(followerId, newUser);
    }
        
        
    }
    
    public void unfollow(int followerId, int followeeId) {
        
        
       user user = uniqueUser.get(followerId);
    if (user != null) {
      user.followee.remove(followeeId);
        
        
        
    }
}}

