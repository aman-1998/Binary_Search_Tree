import java.util.*;
import java.lang.*;

class node
{
	int info;
	node left,right;
	node(int info,node left,node right)
	{
		this.info=info;
		this.left=left;
		this.right=right;
	}
}
class bst
{
	int flag=0;
	node insert(node root,int data)
	{
		if(root==null)
		{
			root=new node(data,null,null);
			return root;
		}
		else if(data<root.info)
			root.left=insert(root.left,data);
		else
			root.right=insert(root.right,data);
		return root;
	}
	void preorder(node root)
	{
		if(root!=null)
		{
			System.out.print(root.info+" ");
			preorder(root.left);
			preorder(root.right);
		}
	}
	void inorder(node root)
	{
		if(root!=null)
		{
			inorder(root.left);
			System.out.print(root.info+" ");
			inorder(root.right);
		}
	}
	void postorder(node root)
	{
		if(root!=null)
		{
			postorder(root.left);
			postorder(root.right);
			System.out.print(root.info+" ");
		}
	}
	int nLeaves(node root)
	{
		if(root==null)
			return 0;
		else if(root.left==null && root.right==null)
			return 1;
		else
			return nLeaves(root.left)+nLeaves(root.right);
	}
	int nHalf(node root)
	{
		if(root==null)
			return 0;
		else if(root.left==null && root.right==null)
			return 0;
		else if(root.left!=null && root.right!=null)
			return nHalf(root.left)+nHalf(root.right);
		else
			return 1+nHalf(root.left)+nHalf(root.right);
	}
	int nFull(node root)
	{
		if(root==null)
			return 0;
		else if(root.left==null && root.right==null)
			return 0;
		else if(root.left!=null && root.right!=null)
			return 1+nFull(root.left)+nFull(root.right);
		else
			return nFull(root.left)+nFull(root.right);
	}
	int count(node root)
	{
		if(root==null)
			return 0;
		else if(root.left==null && root.right==null)
			return 1;
		else
			return 1+count(root.left)+count(root.right);
	}
	boolean search(node root,int data)
	{
		if(root==null)
			return false;
		else if(data<root.info)
			return search(root.left,data);
		else if(data>root.info)
			return search(root.right,data);
		else
			return true;
	}
	boolean isComplete(node root)
	{
		if(root==null)
			return false;
		else if(root.left==null && root.right==null)
			return true;
		else if(root.left!=null && root.right!=null)
			return isComplete(root.left) && isComplete(root.right);
		else
			return false;
	}
	boolean isFull(node root)
	{
		int n;
		if(isComplete(root)==true)
		{
			n=nLeaves(root);
			if((n&(n-1))==0)
				return true;
			else
				return false;
		}
		return false;
	}
	int height(node root)
	{
		int l,r;
		if(root==null)
			return 0;
		else if(root.left==null && root.right==null)
			return 0;
		else
		{
			l=height(root.left);
			r=height(root.right);
			return 1+(l>r?l:r);
		}
	}
	node max(node root)
	{
		node t=root;
		if(root==null)
			return root;
		while(t.right!=null)
			t=t.right;
		return t;
	}
	node min(node root)
	{
		node t=root;
		if(root==null)
			return root;
		while(t.left!=null)
			t=t.left;
		return t;
	}
	node delete(node root,int data)
	{
		if(root==null)
			return root;
		else if(data<root.info)
			root.left=delete(root.left,data);
		else if(data>root.info)
			root.right=delete(root.right,data);
		else
		{
			flag=1;
			//leaf node
			if(root.left==null && root.right==null)
			{
				root=null;
				return root;
			}
			//half node
			else if(root.left!=null && root.right==null)
			{
				node t=root;
				root=root.left;
				return root;
			}
			else if(root.left==null && root.right!=null)
			{
				node t=root;
				root=root.right;
				return root;
			}
			//full node
			else
			{
				node t=max(root.left);
				root.info=t.info;
				root.left=delete(root.left,t.info);
				return root;
			}
		}
		return root;
	}
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		bst x=new bst();
		node root=null;
		node t=null;
		int ch,data,n;
		boolean b;
		while(true)
		{
			System.out.println("\n1.Insert\n2.Preorder\n3.Inorder\n4.Postorder\n5.Leaves\n6.Halfnode\n7.Fullnode\n8.Count\n9.Search\n10.Complete\n11.Full\n12.Height\n13.Max\n14.Min\n15.Delete");
			System.out.print("\nEnter your choice: ");
			ch=in.nextInt();
			switch(ch)
			{
				case 1:
					System.out.print("\nEnter the data: ");
					data=in.nextInt();
					root=x.insert(root,data);
					System.out.println("\n"+data+" is inserted");
					break;
				case 2:
					System.out.print("\nPreorder: ");
					x.preorder(root);
					break;
				case 3:
					System.out.print("\nInorder: ");
					x.inorder(root);
					break;
				case 4:
					System.out.print("\nPostorder: ");
					x.postorder(root);
					break;
				case 5:
					n=x.nLeaves(root);
					System.out.print("\nNumber of leaves= "+n);
					break;
				case 6:
					n=x.nHalf(root);
					System.out.print("\nNumber of half nodes= "+n);
					break;
				case 7:
					n=x.nFull(root);
					System.out.print("\nNumber of full nodes= "+n);
					break;
				case 8:
					n=x.count(root);
					System.out.print("\nNumber of nodes= "+n);
					break;
				case 9:
					System.out.print("\nEnter the element to be searched: ");
					data=in.nextInt();
					b=x.search(root,data);
					if(b==true)
						System.out.println(data+" is found");
					else
						System.out.print(data+" is not found");
					break;
				case 10:
					b=x.isComplete(root);
					if(b==true)
						System.out.print("\nComplete Binary tree");
					else
						System.out.print("\nNot a complete Binary tree");
					break;
				case 11:
					b=x.isFull(root);
					if(b==true)
						System.out.print("\nFull Binary tree");
					else
						System.out.print("\nNot a Full Binary tree");
					break;
				case 12:
					n=x.height(root);
					System.out.println("\nHeight of the tree = "+n);
					break;
				case 13:
					t=x.max(root);
					if(t!=null)
						System.out.println("\nMax value = "+t.info);
					else
						System.out.println("\nTree is empty");
					break;
				case 14:
					t=x.min(root);
					if(t!=null)
						System.out.println("\nMin value = "+t.info);
					else
						System.out.println("\nTree is empty");
					break;
				case 15:
					System.out.print("\nEnter the data to be deleted: ");
					data=in.nextInt();
					x.flag=0;
					root=x.delete(root,data);
					if(x.flag==0)
						System.out.println("\n"+data+" is not found");
					else
						System.out.println("\n"+data+" is deleted");
					break;
				default:
					System.out.println("\nInvalid input");
					break;
			}
		}
	}
}