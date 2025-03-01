package asse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import acsse.csc03a3.Block;
import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * 
 * 
 * 
 * @param <T>
 */
public class Chain<T> {

	
	 private List<Block<T>> blocks;
	 private Blockchain<T> blockchain;
	
	 
	
	 
	 /**
	  * 
	  * A Chain Constructor
	  */
	 public  Chain() {
		  
		 this.blocks = new ArrayList<>();
	     this.blockchain = new Blockchain<>();
	  
	     
	     //Creating Blocks
	     blocks.add(Genesis(blocks));
	 }
	 
	 
	 
	 
	 
	 
	 /**
	  * A Genesis method for a creating a block 
	  * 
	  * @param List of Blocks
	  * @return block
	  */
	 public  Block<T> Genesis(List<Block<T>>  block){
		   
		   
		    // Get the previous block in the block
		    Block<T> previousBlock = block.isEmpty() ? null : block.get(block.size() - 1);
		    
		    
		    String previousHash = previousBlock == null ? "0" : previousBlock.getHash();
		    
		    
		   	    
		    List<Transaction<T>> transactions = new ArrayList<>();
		   
		    return new Block<>(previousHash, transactions);
	 
	 }
	 
	 
	
    
	 /**
	  * 
	  * A method to  Retrieve data using hash
	  * @param hash
	  * @return block
	  */
     public Block<T> getBlockByHash(String hash) {
         
   	  for (Block<T> block : blocks) {
         
   		  if (block.getHash().equals(hash)) {
          
   			  return block;
           }
         
   	  }
        
   	  return null;
  
     }
     
   
     

    
     
     

  /** 
   * Method to get the latest block in the chain
   * 
   * @return
   */
     public Block<T> getLatestBlock() {
         if (!blocks.isEmpty()) {
             return blocks.get(blocks.size() - 1);
         }
         return null;
     }
  
     
  /** Method to get a block by its index in the chain
   * 
   * @param index
   * @return
   */
     public Block<T> getBlockByIndex(int index) {
         if (index >= 0 && index < blocks.size()) {
             return blocks.get(index);
         }
         return null;
     }
     
     
     
  /**
   *  Method to retrieve all blocks in the chain
   * @return
   */
     public List<Block<T>> getAllBlocks() {
         return new ArrayList<>(blocks);
     }

    
     
     
     /**
      * 
      *  Method to remove a block from the chain
      * @param block
      */
     public void removeBlock(Block<T> block) {
         blocks.remove(block);
     }
     
  /**
   * 
   *  Method to find blocks containing a specific transaction
   * @param transaction
   * @return
   */
     public List<Block<T>> findBlocksContainingTransaction(Transaction<T> transaction) {
         List<Block<T>> foundBlocks = new ArrayList<>();
         for (Block<T> block : blocks) {
             if (block.getTransactions().contains(transaction)) {
                 foundBlocks.add(block);
             }
         }
         return foundBlocks;
     }
     
     
     
     
  /**
   * 
   *  Method to get the total number of transactions in the chain
   * @return
   */
     public int getTotalTransactionsCount() {
         int totalTransactions = 0;
         for (Block<T> block : blocks) {
             totalTransactions += block.getTransactions().size();
         }
         return totalTransactions;
     }
     
     
     
     /**
      * 
      *  A method that will retrieve  ALL Patients Medical Records Transactions
      * @return
      */
     @SuppressWarnings("unchecked")
 	public List<PatientsMedicalRecords<T>> patientsMedicalRecordsTransactions() {
     	   
    	 
    	 List<PatientsMedicalRecords<T>> PatientsMedicalRecords = new ArrayList<>();
     	     for (Block<T> block : blocks) {
     	        for (Transaction<T> transaction : block.getTransactions()) {
     	           
     	        	PatientsMedicalRecords.add((PatientsMedicalRecords<T>) transaction.getData());
     	        }
     	    }
     	    return PatientsMedicalRecords;
     	}
      
     
   
     
   
   
     
     
     /***
      * 
      * A method to add transactions into A block
      * @param block
      */
     public void addToBlocks(Block<T> block) {
        
        
         Block<T> newBlock = block;
         Block<T> previousBlock = blocks.isEmpty() ? null : blocks.get(blocks.size() - 1);
         
         newBlock.setNonce(GetNonce(previousBlock));
         newBlock.calculateHash();
         
        
         this.blocks.add(block);
    	
    	 
     }
    
       /**
	     * A method to  Nonce for the new block.
	     * 
	     * @param previousBlock The previous block in the chain.
	     * @return The generated nonce.
	    */
	    private long GetNonce(Block<T> previousBlock) {
	        long previousNonce = previousBlock != null ? previousBlock.getNonce() : 0;
	        long nextNonce = previousNonce + 1;
	        return nextNonce;
	    }
	 
      
	
     
     
     
   
     
     
    
     
    	/**
	 *  A method to add into a block  and block chain and register stake
	 * @param data  
    
	 * 
	 */ 
	 
	
	 @SuppressWarnings("unchecked")
	 public void AddToBlock(PatientsMedicalRecords<T> data) {
		  
	       
		      //Creating the transactions 
		      Transaction<T> transaction = (Transaction<T>) data.createTransaction();
	    	  
		     
		   // Check if the transaction is a duplicate
		        if (isDuplicateTransaction(data.getID())) {
		           
		            
		         // Create a new alert
		              Alert alert = new Alert(AlertType.INFORMATION);
		              alert.setTitle("Patients informaion");
		              alert.setHeaderText(null);
		              alert.setContentText("Opps! The information you have entered already Exist  ");

		              // Show the alert
		              alert.showAndWait();
		            
		            
		            return;
		        }
		      
		      
		      
		    	 // Create a list to hold the transaction
				   List<Transaction<T>> transactions = new ArrayList<>();
				    transactions.add(transaction);
				   
				    
				    if(blocks.isEmpty()) {
				    	  
				    	  System.err.println("The  Block of List  is Empty.... ");
				    	
				      }
				      else
				      {
				       
				        Block<T> previousBlock = blocks.get(blocks.size() - 1);
				       
				        // Create a new block with the previous block's hash and the transaction
				         Block<T> newBlock = new Block<>(previousBlock.getHash(), transactions);
				        
				       
				         // Add the new block to the block list
				          addToBlocks(newBlock);
				           
				          
				         //registering the stake
				         blockchain.registerStake(data.getID(),GetStake());
				            
				            
				         //Checking if the chain is valid
				         blockchain.isChainValid();
				             
				             
				             
				          //adding the transaction into a block chain
				          blockchain.addBlock(transactions);
				              
		    }  
       }
	 
	 
	  /**
      * 
      * A method to check if there are no duplication
      * @param transaction
      * @return
       */
	 
    
	private boolean isDuplicateTransaction(String ID) {
    	 List<Transaction<T>> transactions = new ArrayList<>();
    	 
    	 for (Transaction<T> transaction : transactions) {
             if (transaction.getSender().equals(ID)) {
                 return true;
             }
         }
         return false;
         
         
     }
     
	 
	 
	
	 
  /** 
   * 
   * Method to get a stake number for a node
   * @return
   */
     private int GetStake() {
         Random rand = new Random();
        
         return rand.nextInt(1000); 
     }

	 
	 
	 
	 

	    public List<Block<T>> getBlocksBySender(String sender) {
	        List<Block<T>> senderBlocks = new ArrayList<>();
	        for (Block<T> block : blocks) {
	            for (Transaction<T> transaction : block.getTransactions()) {
	                if (transaction.getSender().equals(sender)) {
	                    senderBlocks.add(block);
	                    break; // No need to check further transactions in this block
	                }
	            }
	        }
	        return senderBlocks;
	    }

	    
	    
	    
	    public List<Block<T>> getBlocksByReceiver(String receiver) {
	        List<Block<T>> receiverBlocks = new ArrayList<>();
	        for (Block<T> block : blocks) {
	            for (Transaction<T> transaction : block.getTransactions()) {
	                if (transaction.getReceiver().equals(receiver)) {
	                    receiverBlocks.add(block);
	                    break; // No need to check further transactions in this block
	                }
	            }
	        }
	        return receiverBlocks;
	    }
     
	    
		 /**
		  * 
		  * 
		  * @return
		  */
	     @SuppressWarnings("rawtypes")
		public String displayChain() {
	        
	    	 StringBuilder builder = new StringBuilder();
	    	    int blockIndex = 0;
	    	    for (Block block : blocks) {
	    	        builder.append("Block ").append(blockIndex).append(":\n");
	    	        builder.append("Previous Hash: ").append(block.getPreviousHash()).append("\n");
	    	        builder.append("Transactions: ").append(block.getTransactions()).append("\n");
	    	        builder.append("Hash: ").append(block.getHash()).append("\n");
	    	        builder.append("Nonce: ").append(block.getNonce()).append("\n");
	    	        builder.append("\n");
	    	        blockIndex++;
	    	    }
	    	    return builder.toString();
	     }
	     
	     
	     
	     
		 
		public String displayBlockchain() {
			
		
		    return blockchain.toString();
			   
	     }
	 
		
	
}
