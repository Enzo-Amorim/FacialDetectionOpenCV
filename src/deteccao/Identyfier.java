package deteccao;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;

public class Identyfier {
    public static void main(String[] args){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat colorfulImage = imread("src\\pessoas\\muai.jpg");
        Mat grayImage = new Mat();

        Imgproc.cvtColor(colorfulImage, grayImage, COLOR_BGR2GRAY);

        CascadeClassifier classifier = new CascadeClassifier("src\\cascades\\haarcascade_frontalface_default.xml");

        MatOfRect detectedFaces = new MatOfRect();

        classifier.detectMultiScale(grayImage, detectedFaces);

        System.out.println(detectedFaces.toArray().length);

        for(Rect rect: detectedFaces.toArray()) {
            System.out.println(rect.x + " " + rect.y + " " + rect.width + " " + rect.height);
            Imgproc.rectangle(colorfulImage, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 2);
        }

        Utils ut = new Utils();
        ut.showImage(ut.convertMatToImage(colorfulImage));

    }
}
