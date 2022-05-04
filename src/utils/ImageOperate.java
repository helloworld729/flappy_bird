package utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ImageOperate {
    /**
     * 旋转图片为指定角度
     *
     * @param BufferedImage
     *   目标图像
     * @param degree
     *   旋转角度
     * @return
     */
    public static BufferedImage rotateimage(final BufferedImage BufferedImage,
                                            final int degree) {
        int w = BufferedImage.getWidth();
        int h = BufferedImage.getHeight();
        int type = BufferedImage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type)).createGraphics())
                .setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(BufferedImage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }

    /**
     * 变更图像为指定大小
     *
     * @param BufferedImage
     *   目标图像
     * @param w
     *   宽
     * @param h
     *   高
     * @return
     */
//    public static BufferedImage resizeimage(final BufferedImage BufferedImage,
//                                            final int w, final int h) {
//        int type = BufferedImage.getcolormodel().gettransparency();
//        BufferedImage img;
//        graphics2d graphics2d;
//        (graphics2d = (img = new BufferedImage(w, h, type)).creategraphics())
//                .setrenderinghint(renderinghints.key_interpolation,
//                        renderinghints.value_interpolation_bilinear);
//        graphics2d.drawimage(BufferedImage, 0, 0, w, h, 0, 0,
//                BufferedImage.getWidth(), BufferedImage.getHeight(), null);
//        graphics2d.dispose();
//        return img;
//    }

    /**
     * 水平翻转图像
     *
     * @param BufferedImage
     *   目标图像
     * @return
     */
//    public static BufferedImage flipimage(final BufferedImage BufferedImage) {
//        int w = BufferedImage.getWidth();
//        int h = BufferedImage.getHeight();
//        BufferedImage img;
//        graphics2d graphics2d;
//        (graphics2d = (img = new BufferedImage(w, h, BufferedImage
//                .getcolormodel().gettransparency())).creategraphics())
//                .drawimage(BufferedImage, 0, 0, w, h, w, 0, 0, h, null);
//        graphics2d.disPose();
//        return img;
//    }
}
