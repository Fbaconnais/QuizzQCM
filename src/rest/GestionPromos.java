package rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bll.BLLException;
import bll.PromotionManager;
import bo.Promotion;

@Path("/promos")
public class GestionPromos {
	
	
	@GET
	@Path("/")
	public List<Promotion> getAllPromos() throws BLLException{
		PromotionManager PMger = PromotionManager.getMger();
		List<Promotion> liste = PMger.selectAllPromos();
		return liste;
	}
	
	@PUT
	@Path("/{id}")
	public Boolean updatePromo(@PathParam("id") String codePromo, @FormParam("libelle") String libelle ) throws RestException {
		Promotion p = new Promotion(codePromo, libelle);
		PromotionManager PMger = PromotionManager.getMger();
		try {
			PMger.updatePromo(p);
		} catch (BLLException e) {
			throw new RestException(e.getMessage(), e);
		}	
		return true;
	}
	@DELETE
	@Path("/{id}")
	public Boolean deletePromo(@PathParam("id") String codePromo) throws RestException {
		PromotionManager PMger = PromotionManager.getMger();
		
		try {
			PMger.removePromotion(codePromo);
		} catch (BLLException e) {
			throw new RestException(e.getMessage(), e);
		}
		
		
		return true;
	}
	@GET
	@Path("/{codePromo}")
	public List<Promotion> getAllPromos(@PathParam("codePromo") String codePromo) throws RestException{
		PromotionManager PMger = PromotionManager.getMger();
		List<Promotion> liste = null;
		try {
			liste = PMger.recherchePromoViaNom(codePromo);
		} catch (BLLException e) {
			throw new RestException(e.getMessage(), e);
		}
		return liste;
	}
}
