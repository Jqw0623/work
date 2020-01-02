package service;

public interface UserService {
    /**
     * 通过某个key得到值，如果key在redis中不存在，到数据库中查
     * @param key
     * @return
     */
    public String getString(String key);
}
