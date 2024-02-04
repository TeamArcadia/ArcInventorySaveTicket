package com.sh.arcinventorysaveticket.nms.tank;

import com.sh.arcinventorysaveticket.nms.wrapper.NBTTagCompoundWrapper;
import lombok.Getter;

import java.lang.reflect.Method;

@Getter
public class NmsNbtTagCompoundUtil {

    private final Class<?> NBTTagCompoundClass;
    private final Method getStringMethod;
    private final Method setStringMethod;

    public NmsNbtTagCompoundUtil() {
        NmsOtherUtil nmsOtherUtil = NmsOtherUtil.getInstance();

        NBTTagCompoundClass = nmsOtherUtil.NBTTagCompound();
        getStringMethod = nmsOtherUtil.NBTTagCompound_getString();
        setStringMethod = nmsOtherUtil.NBTTagCompound_setString();
    }

    public NBTTagCompoundWrapper newInstance() {
        try {
            return new NBTTagCompoundWrapper(NBTTagCompoundClass.newInstance(), this);
        } catch (Exception e) {
            return null;
        }
    }
}