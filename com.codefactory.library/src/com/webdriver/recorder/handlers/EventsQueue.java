package com.webdriver.recorder.handlers;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class EventsQueue implements Queue{

	private Queue eQueue = new LinkedList();
	
	@Override
	public boolean addAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		eQueue.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return eQueue.isEmpty();
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return eQueue.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return eQueue.size();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Object arg0) {
		// TODO Auto-generated method stub
		boolean result = eQueue.add(arg0);
		return result;
	}

	@Override
	public Object element() {
		// TODO Auto-generated method stub
		return eQueue.element();
	}

	@Override
	public boolean offer(Object arg0) {
		// TODO Auto-generated method stub
		return eQueue.offer(arg0);
	}

	@Override
	public Object peek() {
		// TODO Auto-generated method stub		
		return eQueue.element();
	}

	@Override
	public Object poll() {
		// TODO Auto-generated method stub
		return eQueue.poll();
	}

	@Override
	public Object remove() {
		// TODO Auto-generated method stub		
		return eQueue.remove();
	}

}
