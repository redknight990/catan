package entities;

import objects.TexturedMesh;
import resources.GameResources;
import resources.Resource;

public class Table extends EntityStatic {

    public Table(TexturedMesh model) {
        super(model);
    }

    public static Table create ()
    {
        var model = (TexturedMesh)GameResources.get(Resource.MODEL_TABLE);
        return new Table(model);
    }
}