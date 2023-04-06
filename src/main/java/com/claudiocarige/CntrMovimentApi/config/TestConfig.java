package com.claudiocarige.CntrMovimentApi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.claudiocarige.CntrMovimentApi.domain.Client;
import com.claudiocarige.CntrMovimentApi.domain.Container;
import com.claudiocarige.CntrMovimentApi.domain.enums.CategoryCntr;
import com.claudiocarige.CntrMovimentApi.domain.enums.StatusCntr;
import com.claudiocarige.CntrMovimentApi.domain.enums.TypeCntr;
import com.claudiocarige.CntrMovimentApi.repositories.ClientRepository;
import com.claudiocarige.CntrMovimentApi.repositories.ContainerRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ContainerRepository cntrRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Client client = new Client(null, "Pátio dop Porto", "00000000000000", "71000001111", "porto@gmail.com");
		Client client01 = new Client(null, "MkMondial", "33333333333333", "71991125697", "ccarige@gmail.com");
		Client client02 = new Client(null, "TKT", "11111111111111", "71991120000", "tkt@gmail.com");
		Client client03 = new Client(null, "TTT", "22222222222222", "71991122222", "ttt@gmail.com");
		
		
		Container cntr01 = new Container(null, "NTTU0000001", StatusCntr.FULL, TypeCntr.HC40, CategoryCntr.EXPORT, client01);
		Container cntr02 = new Container(null, "NTTU0000002", StatusCntr.EMPTY, TypeCntr.HC20, CategoryCntr.IMPORT, client02);
		Container cntr03 = new Container(null, "NTTU0000003", StatusCntr.EMPTY, TypeCntr.HC40, CategoryCntr.STOPPED, client03);

		clientRepository.saveAll(Arrays.asList(client, client01, client02, client03));
		cntrRepository.saveAll(Arrays.asList(cntr01, cntr02, cntr03));
	}

}
