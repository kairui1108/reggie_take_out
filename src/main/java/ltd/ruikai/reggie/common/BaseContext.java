package ltd.ruikai.reggie.common;

/**
 * 基于ThreadLoca封装工具类，用于保存当前登录用户ID
 * @ author  tanruikai
 * @ date  2022/9/4 21:11
 * @ version 1.0
 */
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrenId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
