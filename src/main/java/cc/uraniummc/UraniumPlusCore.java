package cc.uraniummc;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import lombok.Getter;

import java.io.File;
import java.util.Map;

/**
 * Created by xjboss on 2017/9/4.
 */
@IFMLLoadingPlugin.Name("UraniumPlus")
@IFMLLoadingPlugin.MCVersion("1.7.10")
public class UraniumPlusCore implements IFMLLoadingPlugin {
    @Getter
    private static File location;
    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return UraniumPlusMod.class.getName();
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

        if (data.containsKey("coremodLocation"))
        {
            location = (File) data.get("coremodLocation");
        }
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
