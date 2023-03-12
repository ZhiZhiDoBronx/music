package com.zhizhidobronx.music.Controller;

import com.alibaba.fastjson.JSONObject;
import com.zhizhidobronx.music.domain.Song;
import com.zhizhidobronx.music.service.SongService;
import com.zhizhidobronx.music.utils.Consts;
import com.zhizhidobronx.music.utils.DeleteFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @program: music
 * @description: 歌曲管理
 * @author: ZhiZhio_O
 * @create: 2023-03-10 22:15
 * @other:
 **/

@RequestMapping("/song")
@RestController
public class SongController {
    @Autowired
    private SongService songService;

    /**
    * @Description: 添加歌曲
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zhizhi
    * @Date: 2023/3/10 22:17
    * @throws:
    * @Other:

    */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSong(HttpServletRequest request, @RequestParam("file")MultipartFile mpFile) {
        JSONObject jsonObject = new JSONObject();
        /* 获取前端传来的参数 */
        String singerId = request.getParameter("singerId").trim(); //所属歌手id
        String name = request.getParameter("name").trim(); //歌名
        String introduction = request.getParameter("introduction").trim(); //简介
        String pic = "/img/songPic/tubiao.jpg"; //默认图片
        String lyric = request.getParameter("lyric").trim(); //歌词

        //上传歌曲文件
        if(mpFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"歌曲上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeUrlPath = "/song/"+fileName;
        try {
            mpFile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singerId));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            boolean flag = songService.insert(song);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"保存成功");
                jsonObject.put("avator", storeUrlPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"保存失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"保存失败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }

    /**
    * @Description: 根据歌手id查询歌曲
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zhizhi
    * @Date: 2023/3/10 23:10
    * @throws:
    * @Other:

    */
    @RequestMapping(value = "/singer/detail", method = RequestMethod.GET)
     public Object songOfSingerId(HttpServletRequest request) {
        String singerId = request.getParameter("singerId");
        return songService.songOfSingerId(Integer.parseInt(singerId));
    }


    /**
     * @Description:根据id删除歌曲
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: zhizhi
     * @Date: 2023/2/27 21:28
     * @throws:
     * @Other:

     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteSinger(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        Song song = songService.selectByPrimaryKey(Integer.parseInt(id));
        DeleteFolder.delFloder(song.getUrl().substring(1));
        boolean flag = songService.delete(Integer.parseInt(id));
        return flag;
    }


    /**
     * @Description:根据id修改歌曲信息
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: zhizhi
     * @Date: 2023/2/27 21:19
     * @throws:
     * @Other:

     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSong(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        /* 获取前端传来的参数 */
        String songId = request.getParameter("id").trim(); //所属歌手id
        String name = request.getParameter("name").trim(); //歌名
        String introduction = request.getParameter("introduction").trim(); //简介
        String lyric = request.getParameter("lyric").trim(); //歌词
        /*保存到歌曲的对象中*/
        Song song = new Song();
        song.setId(Integer.parseInt(songId));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setLyric(lyric);
        /*修改*/
        boolean flag = songService.update(song);
        /*处理信息回传*/
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

    /**
     * @Description: 更新歌曲图片
     * @Param: [avatorFile, id]
     * @return: java.lang.Object
     * @Author: zhizhi
     * @Date: 2023/3/2 11:04
     * @throws:
     * @Other:

     */
    @RequestMapping(value = "/updateSongPic",method = RequestMethod.POST)
    public Object updateSongPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"songPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/songPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeAvatorPath);
            boolean flag = songService.update(song);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("pic",storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }

    /**
     * @Description: 歌曲换源
     * @Param: [avatorFile, id]
     * @return: java.lang.Object
     * @Author: zhizhi
     * @Date: 2023/3/2 11:04
     * @throws:
     * @Other:

     */
    @RequestMapping(value = "/updateSong",method = RequestMethod.POST)
    public Object updateSong(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/song/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeAvatorPath);
            boolean flag = songService.update(song);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("avator",storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }
}
