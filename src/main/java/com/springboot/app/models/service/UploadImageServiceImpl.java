package com.springboot.app.models.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadImageServiceImpl implements IUploadImageService {

	private static final String ROOHTPATH = "C://Temps//uploads";
	private static final Log LOG = LogFactory.getLog(UploadImageServiceImpl.class);

	@Override
	public String load(MultipartFile foto) {
		try {

			String nombreUnico = UUID.randomUUID().toString() + foto.getOriginalFilename();
			Path rutaFotoNueva = Paths.get(ROOHTPATH + "//" + nombreUnico);
			LOG.info("Ruta LOAD: " + rutaFotoNueva);
			Files.copy(foto.getInputStream(), rutaFotoNueva);
			return nombreUnico;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(String filename) {
		try {
			Path rutaFotoVieja = Paths.get(ROOHTPATH + "//" + filename);
			if (!filename.equals("noimg.jpg")) {
				Files.delete(rutaFotoVieja);
				LOG.info("Ruta DELETE: " + rutaFotoVieja);
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
