package proclient.command.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import proclient.command.Command;

public class Pages extends Command {
    
    public Pages() {
        super("Pages", "Adds pages to a book", "pages <amount>", "pages");
        
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(!(Minecraft.getMinecraft().thePlayer.getHeldItem() == null)) {
            if(Minecraft.getMinecraft().thePlayer.getHeldItem().getItem().equals("item.writingBook")) {
                if(!Minecraft.getMinecraft().thePlayer.getHeldItem().hasTagCompound()) 
                    Minecraft.getMinecraft().thePlayer.getHeldItem().stackTagCompound = new NBTTagCompound();
                if(!Minecraft.getMinecraft().thePlayer.getHeldItem().getTagCompound().hasKey("pages"))
                    Minecraft.getMinecraft().thePlayer.getHeldItem().stackTagCompound.setTag("pages", new NBTTagList());
                
                for(int i = 0; i < Integer.valueOf(args[0]); i++)
                {
                    String s = "";
                    
                    if(Integer.valueOf(args[0]) == i + 1) {
                        Minecraft.getMinecraft().thePlayer.getHeldItem().getTagCompound().getTagList("pages", 1).appendTag(new NBTTagString(String.valueOf(i)));
                    }
                    else
                    {
                    Minecraft.getMinecraft().thePlayer.getHeldItem().getTagCompound().getTagList("pages", 1).appendTag(new NBTTagString(String.valueOf(i)));
                    }
                }
            }
        }
    }
}