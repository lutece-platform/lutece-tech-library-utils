/*
 * Copyright (c) 2002-2020, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.utils.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Algorithm that will detect if a directed graph has a cycle. <br>
 * A -> B -> C : OK <br>
 * A -> B -> A : KO <br>
 */
public class DetectCycleAlgo
{
    private final Map<Integer, List<Integer>> _graph;

    public DetectCycleAlgo( Map<Integer, List<Integer>> graph )
    {
        this._graph = graph;
    }

    public boolean hasCycle( )
    {
        List<Integer> visited = new ArrayList<>( );
        for ( Integer i : _graph.keySet( ) )
        {
            if ( hasCycle( i, visited ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check to see if the current node has already been visisted. <br>
     * If not, it will check the children of the current node.
     * 
     * @param node
     *            current node
     * @param visited
     *            list of already visited nodes
     * @return true if a cycle is detected
     */
    private boolean hasCycle( int node, List<Integer> visited )
    {
        if ( visited.contains( node ) )
        {
            return true;
        }
        visited.add( node );
        if ( _graph.get( node ) != null )
        {
            for ( Integer nextNode : _graph.get( node ) )
            {
                if ( hasCycle( nextNode, visited ) )
                {
                    return true;
                }
            }
        }
        visited.remove( visited.size( ) - 1 );
        return false;
    }
}
