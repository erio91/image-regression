package com.eriojasn;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        BufferedImage img = null;
        String workingDir = System.getProperty("user.dir");

        try {
            img = ImageIO.read(new File(workingDir + "/img/ps.jpg"));
        } catch (IOException e) {
        }

        int width = img.getWidth();
        int height = img.getHeight();

        double realWidth = 2.5;
        double realHeight = 1600;

        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -height);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        img = op.filter(img, null);

        String xVector = "c(";
        String yVector = "c(";

        for(int i = width - 1; i > 0; i--)
            for(int j = 0; j < height; j++)
                if((img.getRGB(i, j) & 0xff) < 200) {
                    xVector += ((i * 1.0) / width * realWidth) + ",";
                    yVector += ((j * 1.0) / height * realHeight) + ",";
                    //System.out.println(((i * 1.0) / width * realWidth) + "," + ((j * 1.0) / height * realHeight));
                }

        System.out.println(xVector);
        System.out.println(yVector);
    }
}
