package com.kkp.evalapp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.kkp.evalapp.constats.DataStorage;
import com.kkp.evalapp.controller.LoginController;
import com.kkp.evalapp.service.UserService;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;

public class FxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.applicationContext = new SpringApplicationBuilder()
                .sources(EvalappApplication.class)
                .run(args);
    }

    @Override
    public void start(Stage stage) {
        UserService userService = applicationContext.getBean(UserService.class);
        DataStorage dataStorage = DataStorage.getInstance();
        dataStorage.setDepartements(userService.getDepartments ());
        dataStorage.setDivisions   (userService.getDivisions   ());
        dataStorage.setLevels      (userService.getLevels      ());
        dataStorage.setPositions   (userService.getPositions   ());

        // System.out.println(dataStorage.toString());

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(LoginController.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }

}
