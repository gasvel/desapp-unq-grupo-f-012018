package persistence;

import org.apache.log4j.Logger;

import model.Post;

public class PostRepository extends HibernateGenericDAO<Post> implements GenericRepository<Post>{

	private static final long serialVersionUID = 4922222610487751665L;
	public static Logger log = Logger.getLogger(PostRepository.class);

	@Override
	protected Class<Post> getDomainClass() {
		return Post.class;
	}

}
