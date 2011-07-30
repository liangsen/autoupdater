package cz.zweistein.autoupdater.ant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import cz.zweistein.autoupdater.callback.DummyCallback;
import cz.zweistein.autoupdater.definition.FolderParser;
import cz.zweistein.autoupdater.definition.XMLProducer;
import cz.zweistein.autoupdater.definition.vo.Directory;

public class AutoUpdaterDefinition extends Task {
	
	private String srcdir;
	private String destfile;
	
	public void setSrcdir(String srcdir) {
		this.srcdir = srcdir;
	}

	public void setDestfile(String destfile) {
		this.destfile = destfile;
	}

	public void execute() throws BuildException {
		
		try {
			
			FolderParser folderParser = new FolderParser(new DummyCallback());
			Directory directory = folderParser.parse(this.srcdir);
			String content = XMLProducer.createXML(directory);
			File xmlFile = new File(this.destfile);
	
	        FileWriter fileWriter = new FileWriter(xmlFile);
	        fileWriter.write(content);
	        fileWriter.close();
		} catch (IOException e) {
			throw new BuildException(e);
		} catch (ParserConfigurationException e) {
			throw new BuildException(e);
		} catch (TransformerException e) {
			throw new BuildException(e);
		}
        
	}


}