package com.hwangrq.dianping.controller;

import com.hwangrq.dianping.common.*;
import com.hwangrq.dianping.model.UserModel;
import com.hwangrq.dianping.request.LoginRequest;
import com.hwangrq.dianping.request.RegisterRequest;
import com.hwangrq.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author hwangrq
 */
@RestController("/user")
@RequestMapping("/user")
public class UserController {

    public static final String CURRENT_USER_SESSION = "currentUserSession";

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/get/{id}")
    public CommonRes getUser(@PathVariable Integer id) throws BusinessException {
        UserModel userModel = userService.getUser(id);
        if (userModel == null) {
            // return CommonRes.create(new CommonError(EnumBusinessError.NO_OBJECT_FOUND), "fail");
            throw new BusinessException(EnumBusinessError.NO_OBJECT_FOUND);
        } else {
            return CommonRes.create(userModel);
        }
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        String username = "tom";
        ModelAndView modelAndView = new ModelAndView("/index.html");
        modelAndView.addObject("username", username);
        return modelAndView;
    }

    @PostMapping("/register")
    public CommonRes register(@Valid @RequestBody RegisterRequest registerRequest,
                              BindingResult bindingResult)
            throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EnumBusinessError.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult));
        }
        UserModel user = new UserModel();
        user.setTelephone(registerRequest.getTelephone());
        user.setPassword(registerRequest.getPassword());
        user.setNickName(registerRequest.getNickName());
        user.setGender(registerRequest.getGender());

        UserModel resUserModel = userService.register(user);

        return CommonRes.create(resUserModel);
    }

    @RequestMapping("/login")
    @ResponseBody
    public CommonRes login(@RequestBody @Valid LoginRequest loginReq, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EnumBusinessError.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult));
        }
        UserModel userModel = userService.login(loginReq.getTelephone(), loginReq.getPassword());
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION,userModel);
        return CommonRes.create(userModel);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public CommonRes logout() throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        httpServletRequest.getSession().invalidate();
        return CommonRes.create(null);
    }

    /** 获取当前用户信息 */
    @RequestMapping("/getcurrentuser")
    @ResponseBody
    public CommonRes getCurrentUser(){
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);
        return CommonRes.create(userModel);
    }
}
