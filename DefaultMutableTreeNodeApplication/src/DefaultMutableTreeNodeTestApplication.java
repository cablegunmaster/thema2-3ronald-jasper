import java.util.Enumeration;
import javax.swing.tree.*;

/**
Default Mutable Tree node Test Application.
**/
public class DefaultMutableTreeNodeTestApplication
{
	private DefaultMutableTreeNode tree = new DefaultMutableTreeNode( "Person" );
	
	public static void main (String args[]){
		new DefaultMutableTreeNodeTestApplication();
	}
	@SuppressWarnings("unchecked")
	public DefaultMutableTreeNodeTestApplication()
	{
		// Eerst de linker helft van de boom
		DefaultMutableTreeNode employeeNode = new DefaultMutableTreeNode("Employee");
		tree.add(employeeNode);
		
		DefaultMutableTreeNode sales_repNode = new DefaultMutableTreeNode("Sales_Rep"); 
		employeeNode.add(sales_repNode); 
		
		DefaultMutableTreeNode engineerNode = new DefaultMutableTreeNode("Engineer");
		employeeNode.add(engineerNode);
		
		// Nu de rechter helft van de boom
		DefaultMutableTreeNode customerNode = new DefaultMutableTreeNode("Customer");
		tree.add(customerNode);

		DefaultMutableTreeNode usCustomerNode = new DefaultMutableTreeNode("US_Customer");
		customerNode.add(usCustomerNode);

		DefaultMutableTreeNode localCustomerNode = new DefaultMutableTreeNode("Local Customer");
		usCustomerNode.add(localCustomerNode);
		DefaultMutableTreeNode regionalCustomerNode = new DefaultMutableTreeNode("Regional Customer");
		usCustomerNode.add(regionalCustomerNode);
		
		DefaultMutableTreeNode nonUsCustomerNode = new DefaultMutableTreeNode("Non US Customer");
		customerNode.add(nonUsCustomerNode);

	    System.out.println( "PRE ORDER:" );
		Enumeration preorder = tree.preorderEnumeration();
	    while(preorder.hasMoreElements()){
	      System.out.println(preorder.nextElement());
	    }

	    System.out.println( "\n\rPOST ORDER:" );
	    
	    Enumeration postorder = tree.postorderEnumeration();
	    while(postorder.hasMoreElements()){
	      System.out.println(postorder.nextElement());
	    }

	    System.out.println( "\n\rDEPTH ORDER:" );
	    Enumeration dirstDepth = tree.depthFirstEnumeration();
	    while(dirstDepth.hasMoreElements()){
	      System.out.println(dirstDepth.nextElement());
	    }
	}
}
