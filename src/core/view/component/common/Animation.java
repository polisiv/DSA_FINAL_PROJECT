package core.view.component.common;

import core.view.uiconfig.Config;
import java.awt.Dimension;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.interpolation.PropertySetter;
public class Animation {

    private final HeaderButton item;
    private Animator animator;
    private TimingTarget target;

    public Animation(HeaderButton item) {
        this.item = item;
        this.animator = new Animator(200);
        this.animator.setResolution(10);
    }

    public void mouseEntered() {
        stop();
        animator.removeTarget(target);
        target = new PropertySetter(item, "imageSize", item.getImageSize(), Config.HEADER_IMAGE_ACTIVE_SIZE);
        animator.addTarget(target);
        animator.start();
    }

    public void mouseExited() {
        stop();
        animator.removeTarget(target);
        target = new PropertySetter(item, "imageSize", item.getImageSize(), Config.HEADER_IMAGE_INACTIVE_SIZE);
        animator.addTarget(target);
        animator.start();
    }

    public void stop() {
        if (animator.isRunning()) {
            animator.stop();
        }
    }
}
