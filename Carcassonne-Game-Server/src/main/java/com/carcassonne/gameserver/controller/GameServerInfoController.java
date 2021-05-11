package com.carcassonne.gameserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.carcassonne.gameserver.manager.MainGameManager;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping(value = "/gameServerState", produces = "application/json; charset=utf-8")
public class GameServerInfoController {

    @ResponseBody
    @RequestMapping(value = "/getAll",method = RequestMethod.POST)
    public JSONObject getAll(){
        JSONObject result = new JSONObject();
        result.put("info", MainGameManager.getInstance().toString());
        return result;
    }
}
