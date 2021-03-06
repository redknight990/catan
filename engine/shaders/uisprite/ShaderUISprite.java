package shaders.uisprite;

import shaders.Shader;
import shaders.uniform.UniformInt;
import shaders.uniform.UniformMatrix4f;

public class ShaderUISprite extends Shader {

    private static final String VERTEX_FILE = "/shaders/uisprite/vertex.glsl";
    private static final String FRAGMENT_FILE = "/shaders/uisprite/fragment.glsl";

    private static final String ATTRIBUTE_POS = "pos";
    private static final String ATTRIBUTE_UV = "uv";

    public static final int IMAGE_FORMAT_RGBA = 0;
    public static final int IMAGE_FORMAT_ARGB = 1;

    public final UniformMatrix4f modelMatrix = new UniformMatrix4f("modelMatrix");

    public final UniformInt textureSampler = new UniformInt("tex");
    public final UniformInt textureFormat = new UniformInt("textureFormat");

    public ShaderUISprite() {
        super(VERTEX_FILE, FRAGMENT_FILE);
        registerUniforms(
                modelMatrix,
                textureSampler,
                textureFormat
            );
    }

    public void bindAttributes() {
        bindToAttribute(0, ATTRIBUTE_POS);
        bindToAttribute(1, ATTRIBUTE_UV);
    }

}
