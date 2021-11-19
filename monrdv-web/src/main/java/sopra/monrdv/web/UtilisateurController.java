package sopra.monrdv.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sopra.monRdv.model.Type;
import sopra.monRdv.model.Utilisateur;
import sopra.monRdv.repository.IUtilisateurRepository;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {
	
	@Autowired
	private IUtilisateurRepository utilisateurRepo = null;

	@PostMapping({"/connect"})
	public String connect(@RequestParam(required = true) String email,
			@RequestParam(required = true) String motDePasse) {
		Optional<Utilisateur> optUtilisateur = utilisateurRepo.findUtilisateurConnect(email,motDePasse);
		
		if (optUtilisateur.isPresent())
		{
			if(optUtilisateur.get().getType()==Type.PATIENT) {
				return "patient";
			}
			else if(optUtilisateur.get().getType()==Type.PRATICIEN) {
				return "praticien";
			}
			else {
				return "admin";
			}
		}
		else {
			return "home";
		}
		
	}


}
