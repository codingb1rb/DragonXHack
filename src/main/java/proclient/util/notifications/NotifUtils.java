package proclient.util.notifications;

import java.util.ArrayList;
import java.util.List;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.MathHelper;
import proclient.util.shit.RenderUtils;
import proclient.util.Timer;


public class NotifUtils {
	
	static List<Notification> notifications = new ArrayList<Notification>();

	public static Timer timer1 = new Timer();
	
	public static void renderNotifications() {
		FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
		Minecraft mc = Minecraft.getMinecraft();
		int count = 0;
		if(notifications.size() >= 0) { 
			for(int i = notifications.toArray().length-1; i > 0; i--) {
				Notification n = notifications.get(i);
					if(n.notifType == NotifType.INFO) {
						RenderUtils.drawRoundedRect(GuiScreen.width-fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)-50+(int)n.xAdd, GuiScreen.height-80 - (60*(i-1))-60+(int)n.yAdd, fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)+50+3+(int)n.xAdd, 50, 2, 0x90000000);
						float xPositionBar = (float)(System.currentTimeMillis()-n.timer.lastMs)/n.ms;
						if(n.yAdd > 0 && n.xAdd > 0 && !n.timer.hasTimeElapsed(n.ms, false) && n.animTimer.hasTimeElapsed(2, false)) {
							n.yAdd -= 0.35*(System.currentTimeMillis()-n.animTimer.lastMs);
							n.xAdd -= 0.35*(System.currentTimeMillis()-n.animTimer.lastMs);
							n.xAdd = MathHelper.clamp_float(n.xAdd, 0, 10);
							n.yAdd = MathHelper.clamp_float(n.yAdd, 0, 10);
							n.animTimer.reset();
						}
						if(!n.timer.hasTimeElapsed(n.ms, false)) {
							n.animTimer1.reset();
						}
						if(n.timer.hasTimeElapsed(n.ms, false) && GuiScreen.width-fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)-50+n.xAdd >= GuiScreen.width) {
							//addNotification(n.name, n.description, n.ms, n.notifType);
							notifications.remove(i);
						} else if(n.timer.hasTimeElapsed(n.ms, false) && n.animTimer1.hasTimeElapsed(2, false)) {
							n.xAdd += 0.35*(System.currentTimeMillis()-n.animTimer1.lastMs);
							n.animTimer1.reset();
						}
						fr.drawString(n.name, GuiScreen.width-fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)-10+(int)n.xAdd, GuiScreen.height-70 - (60*(i-1))-60+(int)n.yAdd, -1);
						fr.drawString(n.description, GuiScreen.width-fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)-10+(int)n.xAdd, GuiScreen.height-70+fr.FONT_HEIGHT+5 - (60*(i-1))-60+(int)n.yAdd, 0xFF9f9f9f);
						fr.drawString("INFO", (int)(GuiScreen.width-fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)-41+n.xAdd), (int)(GuiScreen.height-60 - (60*(i-1))-60+n.yAdd), -1);
					} else if(n.notifType == NotifType.ERROR) {
						RenderUtils.drawRoundedRect(GuiScreen.width-fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)-60+(int)n.xAdd, GuiScreen.height-80 - (60*(i-1))-60+(int)n.yAdd, fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)+50+3+(int)n.xAdd+10, 50, 2, 0x90000000);
						float xPositionBar = (float)(System.currentTimeMillis()-n.timer.lastMs)/n.ms;
						if(n.yAdd > 0 && n.xAdd > 0 && !n.timer.hasTimeElapsed(n.ms, false) && n.animTimer.hasTimeElapsed(2, false)) {
							n.yAdd -= 0.35*(System.currentTimeMillis()-n.animTimer.lastMs);
							n.xAdd -= 0.35*(System.currentTimeMillis()-n.animTimer.lastMs);
							n.animTimer.reset();
						}
						if(!n.timer.hasTimeElapsed(n.ms, false)) {
							n.animTimer1.reset();
						}
						if(n.timer.hasTimeElapsed(n.ms, false) && GuiScreen.width-fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)-60+n.xAdd >= GuiScreen.width) {
							//addNotification(n.name, n.description, n.ms, n.notifType);
							notifications.remove(i);
						} else if(n.timer.hasTimeElapsed(n.ms, false) && n.animTimer1.hasTimeElapsed(2, false)) {
							n.xAdd += 0.35*(System.currentTimeMillis()-n.animTimer1.lastMs);
							n.animTimer1.reset();
						}
						fr.drawString(n.name, GuiScreen.width-fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)-10+(int)n.xAdd, GuiScreen.height-70 - (60*(i-1))-60+(int)n.yAdd, -1);
						fr.drawString(n.description, GuiScreen.width-fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)-10+(int)n.xAdd, GuiScreen.height-70+fr.FONT_HEIGHT+5 - (60*(i-1))-60+(int)n.yAdd, 0xFF9f9f9f);
						fr.drawString("ERROR", (int)(GuiScreen.width-fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)-51+n.xAdd), (int)(GuiScreen.height-60 - (60*(i-1))-60+n.yAdd), 0xFFFF0000);
					} else if(n.notifType == NotifType.WARNING) {
						RenderUtils.drawRoundedRect(GuiScreen.width-fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)-70+(int)n.xAdd, GuiScreen.height-80 - (60*(i-1))-60+(int)n.yAdd, fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)+50+3+(int)n.xAdd+20, 50, 2, 0x90000000);
						float xPositionBar = (float)(System.currentTimeMillis()-n.timer.lastMs)/n.ms;
						if(n.yAdd > 0 && n.xAdd > 0 && !n.timer.hasTimeElapsed(n.ms, false) && n.animTimer.hasTimeElapsed(2, false)) {
							n.yAdd -= 0.35*(System.currentTimeMillis()-n.animTimer.lastMs);
							n.xAdd -= 0.35*(System.currentTimeMillis()-n.animTimer.lastMs);
							n.animTimer.reset();
						}
						if(!n.timer.hasTimeElapsed(n.ms, false)) {
							n.animTimer1.reset();
						}
						if(n.timer.hasTimeElapsed(n.ms, false) && GuiScreen.width-fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)-60+n.xAdd >= GuiScreen.width) {
							//addNotification(n.name, n.description, n.ms, n.notifType);
							notifications.remove(i);
						} else if(n.timer.hasTimeElapsed(n.ms, false) && n.animTimer1.hasTimeElapsed(2, false)) {
							n.xAdd += 0.35*(System.currentTimeMillis()-n.animTimer1.lastMs);
							n.animTimer1.reset();
						}
						fr.drawString(n.name, GuiScreen.width-fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)-10+(int)n.xAdd, GuiScreen.height-70 - (60*(i-1))-60+(int)n.yAdd, -1);
						fr.drawString(n.description, GuiScreen.width-fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)-10+(int)n.xAdd, GuiScreen.height-70+fr.FONT_HEIGHT+5 - (60*(i-1))-60+(int)n.yAdd, 0xFF9f9f9f);
						fr.drawString("WARNING", (int)(GuiScreen.width-fr.getStringWidth(fr.getStringWidth(n.name) > fr.getStringWidth(n.description) ? n.name : n.description)-61+n.xAdd), (int)(GuiScreen.height-60 - (60*(i-1))-60+n.yAdd), 0xFFffe664);
					}
				}
			}
		}
	
	public static void addNotification(String name, String description, long ms, NotifType notifType) {
		notifications.add(new Notification(name,description,ms,notifType));
	}
	
}