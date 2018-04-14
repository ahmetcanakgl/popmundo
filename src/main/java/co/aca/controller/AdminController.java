package co.aca.controller;

import co.aca.error.EntityNotFoundException;
import co.aca.model.Admin;
import co.aca.model.PushModel;
import co.aca.model.response.ResponseAdmin;
import co.aca.service.AndroidPushNotificationsService;
import co.aca.service.imp.IAdminService;
import co.aca.util.LangString;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/api/")
@SuppressWarnings("unchecked")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @RequestMapping("/hi")
    public @ResponseBody
    String hiThere() {
        return "hello world!";
    }

    @GetMapping("get_users")
    public ResponseEntity<List<Admin>> getAllAdmins(@RequestParam("p") Integer p) throws EntityNotFoundException {
        if (p == 13) {
            List<Admin> list = adminService.getAdminList();
            return new ResponseEntity(new ResponseAdmin(list), HttpStatus.OK);
        }else{
            return new ResponseEntity(new ResponseAdmin("Nope.", "0"), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("get_user")
    public ResponseEntity<Admin> getAdminById(@RequestParam("id") Integer id) throws EntityNotFoundException {
        Admin admin = adminService.getAdminById(id);
        if (admin == null) {
            return new ResponseEntity(new ResponseAdmin(new LangString().getNoSuchAnAdmin("tr"), "0"), HttpStatus.OK);
        }

        return new ResponseEntity(new ResponseAdmin(admin), HttpStatus.OK);
    }

    @PostMapping("add_user")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) throws EntityNotFoundException {

        admin.setCreatedAt(getDate());
        boolean flag = adminService.addAdmin(admin);
        if (!flag) {
            return new ResponseEntity(new ResponseAdmin(new LangString().getAdminExists("tr"), "0"), HttpStatus.OK);
        }

        PushModel pushModel = new PushModel();
        pushModel.setOpType("1");
        pushModel.setData("data");
        pushModel.setMessage(admin.getScriptType());
        pushModel.setType("0");
        pushModel.setTitle(admin.getIngameName());

        addPersonPush(pushModel);

        return new ResponseEntity(new ResponseAdmin(new LangString().getAdded("tr"), "1"), HttpStatus.OK);
    }

    private Date getDate() {

        DateTime dt = new DateTime();
        DateTimeZone dtZone = DateTimeZone.forOffsetHoursMinutes(3, 0);
        DateTime dtus = dt.withZone(dtZone);

        return dtus.toLocalDateTime().toDate();
    }

    private void addPersonPush(PushModel pushModel) {

        String locale = "tr";

        JSONObject body = new JSONObject();
        JSONObject notification = new JSONObject();
        JSONObject data = new JSONObject();

        notification.put("title", pushModel.getTitle());
        data.put("title", pushModel.getTitle());

        notification.put("body", pushModel.getMessage());
        data.put("message", pushModel.getMessage());
        data.put("data", pushModel.getData());
        data.put("type", pushModel.getType());
        body.put("to", "/topics/superAdmin");
        body.put("priority", "high");
        body.put("notification", notification);
        body.put("data", data);

        HttpEntity<String> request = new HttpEntity<>(body.toString());

        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();
    }
}