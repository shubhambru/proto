package com.ecom.proto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.proto.model.CartItemDTO;

@Repository
public interface CartRepository extends JpaRepository<CartItemDTO , String> {
    
    @Query(value = "SELECT * FROM Cart i WHERE i.uid = :uid" , nativeQuery = true)
	public List<CartItemDTO> getCartItems(@Param("uid") long uid);

    @Query(value = "SELECT COUNT(i) FROM Cart i WHERE i.product_id = :itemID AND i.uid = :uid" , nativeQuery = true)
	public long checkCartItem(@Param("uid") long uid, @Param("itemID") long itemID);

    @Query(value = "SELECT * FROM Cart i WHERE i.product_id = :itemID AND i.uid = :uid" , nativeQuery = true)
    public CartItemDTO getCartItem(@Param("uid") long uid, @Param("itemID") long itemID);
}
