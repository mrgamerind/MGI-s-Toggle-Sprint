package com.mrgamerind.togglesprint;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ToggleSprintModClient implements ClientModInitializer {
    public static boolean toggleSprint = false;
    private static KeyBinding toggleSprintKey;

    @Override
    public void onInitializeClient() {
        toggleSprintKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.togglesprint.toggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            "category.togglesprint"
        ));

        net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleSprintKey.wasPressed()) {
                toggleSprint = !toggleSprint;
            }

            if (toggleSprint && client.player != null) {
                client.options.sprintKey.setPressed(true);
            }
        });
    }
}
