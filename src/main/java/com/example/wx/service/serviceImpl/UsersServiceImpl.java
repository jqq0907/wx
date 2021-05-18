package com.example.wx.service.serviceImpl;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.wx.Entity.Submit;
import com.example.wx.Entity.Users;
import com.example.wx.mapper.UsersMapper;
import com.example.wx.service.UsersService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author "
 * @since 2021-05-16
 */
@Log4j2
@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersMapper usersMapper;
    @Value("${wx.loginUrl}")
    private String loginUrl;
    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.appSecret}")
    private String appSecret;

    /**
     * 微信端登录获取openId
     *
     * @param code     code
     * @param userName 微信用户名称
     * @return
     */
    @Override
    public String loginWx(String code, String userName) {
        HttpResponse execute = HttpUtil.createGet(loginUrl)
                .form("appid", appId)
                .form("js_code", code)
                .form("secret", appSecret)
                .form("grant_type", "authorization_code")
                .charset(CharsetUtil.CHARSET_UTF_8)
                .execute();
        String body = execute.body();
        Map<String, Object> map = (Map<String, Object>) JSONObject.parse(body);
        String openId = (String) map.get("openid");
        try {
            Users vo = usersMapper.selectOne(new QueryWrapper<Users>().eq("u_id", openId));
            // 用户第一次登录则插入，非第一次则更新
            if (ObjectUtil.isNull(vo)) {
                Users users = new Users();
                // 用户id
                users.setUId(openId);
                // 用户名称
                users.setUName(userName);
                // 用户活跃时间
                users.setUActiveTime(LocalDateTime.now());
                usersMapper.insert(users);
            } else {
                // 用户活跃时间
                vo.setUActiveTime(LocalDateTime.now());
                usersMapper.update(vo, new UpdateWrapper<Users>()
                        .eq("u_id", vo.getUId()));
            }
            return openId;
        } catch (Exception e) {
            log.error("微信登录失败", e);
            return "";
        }
    }
}
