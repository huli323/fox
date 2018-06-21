package foxutil;

import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SftpUtil {

    public static ChannelSftp connect(String host, int port, String username, String password) throws Exception {
        return connect(host, port, username, password, 0);
    }

    /**
     * @param host ip
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @param timeOut 超时时间
     * @return
     */
    public static ChannelSftp connect(String host, int port, String username, String password, int timeOut) throws Exception {
        JSch jSch = new JSch();
        try {
            Session session = jSch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(timeOut);
            Channel channel = session.openChannel("sftp");
            channel.connect(timeOut);
            return (ChannelSftp) channel;
        } catch (Exception e) {
            throw e;
        }
    }


    public static void upload(ChannelSftp sftp, String localPath, String remotePath, String fileName) throws Exception {
        upload(sftp, localPath, remotePath, fileName, fileName);
    }

    /**
     * @param sftp
     * @param localPath 本地目录
     * @param remotePath 远程目录
     * @param fileName 本地文件名
     * @param remoteFileName 远程文件名
     * @throws Exception
     */
    public static void upload(ChannelSftp sftp, String localPath, String remotePath, String fileName, String remoteFileName) throws Exception {
        try {
            sftp.cd(remotePath);
            FileInputStream fileInputStream = new FileInputStream(localPath + fileName);
            sftp.put(fileInputStream, remoteFileName);
        } catch (Exception e){
            throw e;
        } finally {
            disconnect(sftp);
        }

    }

    public static void download(ChannelSftp sftp, String remotePath, String fileName, String localPath) throws Exception {
        download(sftp, remotePath, fileName, localPath, fileName);
    }

    public static void download(ChannelSftp sftp, String remotePath, String fileName, String localPath, String localFileName) throws Exception {
        try{
            sftp.cd(remotePath);

            // 本地目录不存在则创建
            File file = new File(localPath);
            if(!file.exists()){
                file.mkdirs();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(new File(localPath + localFileName));
            sftp.get(fileName, fileOutputStream);
        } catch (Exception e){
            throw e;
        } finally {
            disconnect(sftp);
        }
    }

    public static void disconnect(ChannelSftp sftp){
        try {
            sftp.getSession().disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws SftpException {
        String host = "118.126.65.75";
        String username = "root";
        String password = "ifox94323";
        String remotePath = "/usr/local/";
        String localPath = "C:\\Users\\Administrator\\Desktop\\";

        try {
            ChannelSftp sftp = connect(host, 22, username, password);

            download(sftp, remotePath, "test", localPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
