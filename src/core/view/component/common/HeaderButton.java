package core.view.component.common;
   
import core.view.uiconfig.Config;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JButton;

public class HeaderButton extends JButton {

    public Image getImage() {
        return image;
    }

    public void setImage(Image image, Color color) {
        if (image == null) {
            this.image = null;
            return;
        }

        // Convert Image to BufferedImage (ARGB for transparency)
        BufferedImage bufferedImage = new BufferedImage(
            image.getWidth(null),
            image.getHeight(null),
            BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g2d = bufferedImage.createGraphics();
        // Draw the original image onto the buffered image
        g2d.drawImage(image, 0, 0, null);

        g2d.setComposite(AlphaComposite.SrcAtop);  
        g2d.setColor(color);  
        g2d.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        g2d.dispose();

        this.image = bufferedImage;
    }

    public Dimension getImageSize() {
        return imageSize;
    }

    public void setImageSize(Dimension imageSize) {
        this.imageSize = imageSize;
        repaint();
    }

    private final Animation animation;
    private Image image;
    private Dimension imageSize = Config.HEADER_IMAGE_INACTIVE_SIZE;

    public HeaderButton() {
        setContentAreaFilled(false);
        setBorder(null);
        animation = new Animation(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                animation.mouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                animation.mouseExited();
            }
            
            @Override
            public void mouseClicked(MouseEvent me) {
                animation.mouseExited();
            }
        });
    }


    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        int width = getWidth();
        int height = getHeight();
        int x = (width - imageSize.width) / 2;
        int y = (height - imageSize.height) / 2;
        g2.drawImage(image, x, y, imageSize.width, imageSize.height, null);
        g2.dispose();
        super.paintComponent(grphcs);
    }
}