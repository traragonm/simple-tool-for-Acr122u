/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NFC;

import java.util.ArrayList;
import javax.smartcardio.CommandAPDU;

/**
 *
 * @author minh
 */
public class Commands {

    public static String getCardType(byte[] ATR) {
        if (ATR[13] == 0x00) {
            switch(ATR[14]){
                case 0x01:
                    return "mifare 1K";
                case 0x02:
                    return "mifare 4K";
                case 0x03:
                    return "mifare Ultralight";
                case 0x04:
                    return "mifare Mini";
            }
        }
        else if(ATR[13]==0xF0){
            switch(ATR[14]){
                case 0x04:
                    return "Topaz and jewel";
                case 0x11:
                    return "FeliCa 212K";
                case 0x12:
                    return "FeliCa 414K";
            }
        }
        return "unidentifed";
    }
    public static CommandAPDU getUIDCommand(){
        byte[] readUIDCmd = new byte[]{(byte) 0xFF,(byte) 0xCA,(byte) 0x00,(byte) 0x00,(byte) 0x00};
        return new CommandAPDU(readUIDCmd);
    }
    public static CommandAPDU getDefaultAuthenticationKeyCommand(){
        byte[] defaultAuthenticationKey = new byte[] {(byte)0xFF,(byte)0x82,(byte)0x00,(byte)0x00,(byte)0x06,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF};
        return new CommandAPDU(defaultAuthenticationKey);
    }
    public static CommandAPDU getManualAuthenticationKeyCommand(byte[] key){
        
        byte[] manualAuthenticationKeyCmd = new byte[] {(byte)0xFF,(byte)0x82,(byte)0x00,(byte)0x00,(byte)0x06,key[0],key[1],key[2],key[3],key[4],key[5]};
        return new CommandAPDU(manualAuthenticationKeyCmd);
    }
    public static CommandAPDU getAuthenticationWithKeyACommand(byte blocknum){
        byte[] authCmdForkeyA = new byte[] {(byte) 0xFF, (byte) 0x86, (byte) 0x00, (byte) 0x00, (byte) 0x05, (byte) 0x01,(byte) 0x00,blocknum,(byte) 0x60,(byte) 0x00};
        return new CommandAPDU(authCmdForkeyA);
    }
    public static CommandAPDU getAuthenticationWithKeyBCommand(byte blocknum){
        byte[] authCmdForkeyB = new byte[] {(byte) 0xFF, (byte) 0x86, (byte) 0x00, (byte) 0x00, (byte) 0x05, (byte) 0x01,(byte) 0x00,blocknum,(byte) 0x61,(byte) 0x00};
        return new CommandAPDU(authCmdForkeyB);
    }
    public static CommandAPDU getReadBlockCommand(byte blocknum){
        byte[] readBlockCmd = new byte[]{(byte)0xFF,(byte)0xB0,(byte)0x00,blocknum,(byte)0x10};
        return new CommandAPDU(readBlockCmd);
    }
    public static CommandAPDU getWriteBlockCommand(byte blocknum,byte[] data){
        byte[] writeCardCmd = new byte[]{(byte)0xFF,(byte)0xD6,(byte)0x00,blocknum,(byte)0x10,
            data[0],data[1],data[2],data[3],data[4],data[5],data[6],
            data[7],data[8],data[9],data[10],data[11],data[12],data[13],
            data[14],data[15]};
        return new CommandAPDU(writeCardCmd);
    }
    public static CommandAPDU getFormatCommand(byte blocknum){
        byte[] formatCard = new byte[]{(byte)0xFF,(byte)0xD6,(byte)0x00,blocknum,(byte)0x10,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0x07,(byte)0x80,(byte)0x69,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF};
        return new CommandAPDU(formatCard);
    }
}
