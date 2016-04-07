package de.fu_berlin.agdb.importer.dwd.core;

import org.apache.commons.net.ftp.FTPFile;

public interface IFTPFileWorkerProvider {
	public FTPFile getFTPFileToWorkWith();
}
