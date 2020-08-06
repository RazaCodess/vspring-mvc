package com.rab3tech.dao;

import java.util.List;

public interface ProfileDao {

	void show();

	String updateSignup(ProfileDTO profileDTO);

	String createSignup(ProfileDTO profileDTO);

	List<ProfileDTO> sortProfiles(String sort);

	List<String> findAllQualification();

	List<ProfileDTO> filterProfiles(String filterText);

	List<ProfileDTO> searchProfiles(String search);

	List<ProfileDTO> findAll();

	ProfileDTO findByEmail(String pemail);

	ProfileDTO findByUsername(String pusername);

	void deleteByUsername(String pusername);

	ProfileDTO authUser(String pusername, String ppassword);

	String findPasswordByUsernameOrEmail(String pusernameEmail);

	String icreateSignup(ProfileDTO profileDTO);

	byte[] findPhotoByUsername(String pusername);

	List<ProfileDTO> findAllWithPhoto();

}
