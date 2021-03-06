package ui;

public class UIColor {

	public static final UIColor WHITE = new UIColor(1, 1, 1, 1.0f);

	public static final UIColor RED = new UIColor(1.0f, 0, 0, 1.0f);
	public static final UIColor GREEN = new UIColor(0.17f, 0.68f, 0.31f, 1.0f);
	public static final UIColor BLUE = new UIColor(0, 0, 1.0f, 1.0f);

	public static final UIColor CYAN = new UIColor(0, 1.0f, 1.0f, 1.0f);
	public static final UIColor MAGENTA = new UIColor(1.0f, 0, 1.0f, 1.0f);
	public static final UIColor YELLOW = new UIColor(1.0f, 1.0f, 0, 1.0f);

	public static final UIColor BLACK = new UIColor(0, 0, 0, 1.0f);
	
	public static final UIColor LIGHT_GRAY = new UIColor(0.8f, 0.8f, 0.8f, 1.0f);
	public static final UIColor DARK_GRAY = new UIColor(0.4f, 0.4f, 0.4f, 1.0f);
    public static final UIColor ORANGE = new UIColor(1.000f, 0.647f, 0.000f, 1.0f);
	public static final UIColor PURPLE = new UIColor(0.502f, 0.000f, 0.502f, 1.0f);

    private final float r, g, b, a;
	
	public UIColor(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	public UIColor(UIColor src) {
		if (src == null) {
			r = g = b = a = 1.0f;
			return;
		}
		this.r = src.r;
		this.g = src.g;
		this.b = src.b;
		this.a = src.a;
	}
	
	public float getR() {
		return r;
	}
	
	public float getG() {
		return g;
	}
	
	public float getB() {
		return b;
	}
	
	public float getA() {
		return a;
	}
	
}
