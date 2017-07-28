/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobalogin;

import cobalogin.exceptions.NonexistentEntityException;
import cobalogin.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author hatma
 */
public class TabelhatmaJpaController implements Serializable {

    public TabelhatmaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tabelhatma tabelhatma) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tabelhatma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTabelhatma(tabelhatma.getNama()) != null) {
                throw new PreexistingEntityException("Tabelhatma " + tabelhatma + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tabelhatma tabelhatma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tabelhatma = em.merge(tabelhatma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tabelhatma.getNama();
                if (findTabelhatma(id) == null) {
                    throw new NonexistentEntityException("The tabelhatma with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tabelhatma tabelhatma;
            try {
                tabelhatma = em.getReference(Tabelhatma.class, id);
                tabelhatma.getNama();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tabelhatma with id " + id + " no longer exists.", enfe);
            }
            em.remove(tabelhatma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tabelhatma> findTabelhatmaEntities() {
        return findTabelhatmaEntities(true, -1, -1);
    }

    public List<Tabelhatma> findTabelhatmaEntities(int maxResults, int firstResult) {
        return findTabelhatmaEntities(false, maxResults, firstResult);
    }

    private List<Tabelhatma> findTabelhatmaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tabelhatma.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tabelhatma findTabelhatma(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tabelhatma.class, id);
        } finally {
            em.close();
        }
    }

    public int getTabelhatmaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tabelhatma> rt = cq.from(Tabelhatma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
