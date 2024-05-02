package datos.lab2.frame;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class HelloApplication extends Application {
    private Random Random = new Random();
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    @Override
    public void start(Stage stage) throws IOException {
        archivo arch = new archivo();
        arch.leercsv("src/main/resources/flights_final1.csv");
        //Sphere sphere = prepareSolidSphere();;

        smartGroup group = new smartGroup();
        //group.getChildren().add(sphere);

        Camera camera = new PerspectiveCamera();

        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(javafx.scene.paint.Color.WHITE);
        scene.setCamera(camera);

        group.translateXProperty().set(WIDTH / 2);
        group.translateYProperty().set(HEIGHT / 2);
        group.translateZProperty().set(0);

        Group puntosAeropuertos = new Group(); // Grupo para los puntos de los aeropuertos

        // Dentro del método start()
        distributeSpheresInSphere(puntosAeropuertos, arch.aeropuertos, 300, 10);





        group.getChildren().add(puntosAeropuertos); // Agregar el grupo de puntos al grupo principal

        initMouseControl(group, scene, stage);

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event ->{
            switch (event.getCode()){
                case Z:
                    data a = new data(arch);
                    a.setVisible(true);
                    break;

            }

        });

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private boolean hasCollision(Sphere newSphere, List<Sphere> existingSpheres, double minDistance) {
        for (Sphere sphere : existingSpheres) {
            double distance = Math.sqrt(
                    Math.pow(newSphere.getTranslateX() - sphere.getTranslateX(), 2) +
                            Math.pow(newSphere.getTranslateY() - sphere.getTranslateY(), 2) +
                            Math.pow(newSphere.getTranslateZ() - sphere.getTranslateZ(), 2)
            );
            if (distance < minDistance) {
                return true; // Hay colisión
            }
        }
        return false; // No hay colisión
    }

    private void distributeSpheresInSphere(Group puntosAeropuertos, List<aeropuerto> vuelos, double radius, double minDistance) {
        List<Sphere> generatedSpheres = new ArrayList<>();

        for (aeropuerto aeropuerto : vuelos) {
            double latitudRad = Math.toRadians(aeropuerto.latitud);
            double longitudRad = Math.toRadians(aeropuerto.longitud);

            // Convertir coordenadas esféricas a cartesianas
            double x = radius * Math.cos(latitudRad) * Math.cos(longitudRad);
            double y = radius * Math.sin(latitudRad);
            double z = radius * Math.cos(latitudRad) * Math.sin(longitudRad);

            Sphere newSphere = new Sphere(3);
            Random random = new Random();

            // Generar valores aleatorios para los componentes RGB
            double red = random.nextDouble();
            double green = random.nextDouble();
            double blue = random.nextDouble();

            // Crear un color aleatorio
            Color randomColor = new Color(red, green, blue, 1.0);
            newSphere.setMaterial(new PhongMaterial(randomColor));
            newSphere.setTranslateX(x);
            newSphere.setTranslateY(y);
            newSphere.setTranslateZ(z);

            // Verificar colisiones con las esferas existentes
            if (!hasCollision(newSphere, generatedSpheres, minDistance)) {
                generatedSpheres.add(newSphere);
                puntosAeropuertos.getChildren().add(newSphere);
            }
        }
    }





    private void initMouseControl(smartGroup group, Scene scene, Stage stage){
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event ->{
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event ->{
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });
        stage.addEventHandler(ScrollEvent.SCROLL, event ->{
            double delta = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() - delta);
        });
    }
    public static void main(String[] args) {

        launch();
    }

    class smartGroup extends Group{
        Rotate r;
        Transform t = new Rotate();
        void rotateByX(int ang){
            r = new Rotate(ang, Rotate.X_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().add(t);
        }
        void rotateByY(int ang){
            r = new Rotate(ang, Rotate.Y_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().add(t);
        }

    }
}