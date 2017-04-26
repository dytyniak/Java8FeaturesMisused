package github.olegts.java8.misused.lambda.collections;

import github.olegts.java8.misused.User;
import github.olegts.java8.misused.Annotations.Good;
import github.olegts.java8.misused.Annotations.Ugly;

import java.util.HashMap;
import java.util.Map;

public class MapIterating {
    @Ugly
    class UsingOldGoodEntrySet{
        public Map<String, String> getUserNames(Map<String, User> users){
            Map<String, String> userNames = new HashMap<>();
            users.entrySet().forEach(user ->
                    userNames.put(user.getKey(), user.getValue().getName()));
            return userNames;
        }
    }

    @Good
    class UsingMapForEach{
        public Map<String, String> getUserNames(Map<String, User> users){
            Map<String, String> userNames = new HashMap<>();
            users.forEach((key, value) -> userNames.put(key, value.getName()));
            return userNames;
        }
    }
}