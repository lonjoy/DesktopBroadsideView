package iqq.app.ui.widget.hideframe;

/**
 * @author ZhiHui_Chen<6208317@qq.com>
 * @create date 2013-4-3
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MsgboxFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = -496898429932291825L;

	private int sideWidth = 100; // �߿��
	private int side = 5; // ����ʾ�������غ�ɼ�������Ϊ1
	private int gap = 0;

	private Rectangle rect;
	private int frameLeft;// ��������Ļ��ߵľ���
	private int frameRight;// ��������Ļ�ұߵľ��룻
	private int frameTop;// ��������Ļ�����ľ���
	private int frameWidth; // ����Ŀ�
	private int frameHeight; // ����ĸ�

	private int screenXX;// ��Ļ�Ŀ�ȣ�
	private Point point; // ����ڴ����λ��

	private Timer timer = new Timer(300, this);
	private IPosition position;

	public MsgboxFrame() {

	}

	public void start() {
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		frameLeft = (int) getBounds().getX();
		frameTop = (int) getBounds().getY();
		frameWidth = getWidth();
		frameHeight = getHeight();
		screenXX = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		frameRight = screenXX - frameLeft - frameWidth;

		// ��ȡ���������
		rect = new Rectangle(0, 0, frameWidth, frameHeight);
		// ��ȡ����ڴ����λ��
		point = getMousePosition();

		if (position == Positions.LEFT) {
			if (frameLeft < 0 && isPtInRect(rect, point)) {
				setLocation(0, frameTop); // ��������ߣ����ָ������ʾ���壻
			} else if (frameLeft > -side + 1 && frameLeft < side + 1
					&& !(isPtInRect(rect, point))) {
				setLocation(frameLeft - frameWidth + side, frameTop); // �����Ƶ���߱�Ե���ص���ߣ�
			}
		} else if (position == Positions.TOP) {
			if (frameTop < 0 && isPtInRect(rect, point)) {
				setLocation(frameLeft, frameTop + side); // ��������ߣ����ָ������ʾ���壻
			} else if (frameTop > -side + 1 && frameTop < side + 1
					&& !(isPtInRect(rect, point))) {
				setLocation(frameLeft, frameTop - frameHeight + side); // �����Ƶ���Ļ������
			}
		} else if (position == Positions.RIGHT) {
			if (frameRight < 0 && isPtInRect(rect, point)) {
				setLocation(screenXX - frameWidth, frameTop);// ���ָ������ʾ��
			} else if (frameRight > -side - 1 && frameRight < side + 1
					&& !(isPtInRect(rect, point))) {
				setLocation(screenXX - side, frameTop); // �����Ƶ���Ļ������
			}
		}

	}

	/**
	 * ����Ƿ��ھ��ο���
	 * 
	 * @param rect
	 * @param point
	 * @return
	 */
	public boolean isPtInRect(Rectangle rect, Point point) {
		if (rect != null && point != null) {
			int x0 = rect.x;
			int y0 = rect.y;
			int x1 = rect.width;
			int y1 = rect.height;
			int x = point.x;
			int y = point.y;

			return x >= x0 && x < x1 && y >= y0 && y < y1;
		}
		return false;
	}

	public void setPosition(IPosition position, int sideWidth, int side) {
		setPosition(position, sideWidth, side, 0);
	}

	public void setPosition(IPosition position, int sideWidth, int side, int gap) {
		this.position = position;
		this.side = side;
		this.gap = gap;
		setBounds(position.getPosition(sideWidth, side, gap));
	}

	public static void main(String[] args) {
		MsgboxFrame frame = new MsgboxFrame();
		frame.setPosition(Positions.RIGHT, 100, 10, 100); // ��������Ļ��ʾ�ط�
		frame.getContentPane().setBackground(new Color(20, 20, 20));
		frame.start();
		frame.setVisible(true);

		JLabel title = new JLabel("Message Box");
		title.setForeground(Color.WHITE);
		JPanel p = new JPanel();
		p.setBackground(new Color(20, 20, 20));
		p.add(title, BorderLayout.CENTER);
		frame.add(p, BorderLayout.PAGE_START);
	}
}