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
package fr.paris.lutece.plugins.algo;

import fr.paris.lutece.plugins.utils.algo.DetectCycleAlgo;
import fr.paris.lutece.plugins.utils.algo.helper.DetectCycleGraphBuilder;
import junit.framework.TestCase;

public class DetecCycleAlgoTest extends TestCase
{
    /**
     * A -> B -> C
     */
    public void testSimpleGraph( )
    {
        DetectCycleAlgo algo = DetectCycleGraphBuilder.builder( ).addToGraph( 1, 2 ).addToGraph( 2, 3 ).build( );

        assertFalse( algo.hasCycle( ) );
    }

    /**
     * A -> B -> A
     */
    public void testSimpleCycle( )
    {
        DetectCycleAlgo algo = DetectCycleGraphBuilder.builder( ).addToGraph( 1, 2 ).addToGraph( 2, 1 ).build( );

        assertTrue( algo.hasCycle( ) );
    }

    /**
     * A -> B -> C <br>
     * A -> D - > E - > 
     */
    public void testBranchGraph_OK( )
    {
        DetectCycleAlgo algo = DetectCycleGraphBuilder.builder( ).addToGraph( 1, 2 ).addToGraph( 2, 3 ).addToGraph( 1, 4 ).addToGraph( 4, 5 ).addToGraph( 5, 3 )
                .build( );

        assertFalse( algo.hasCycle( ) );
    }

    /**
     * A -> B -> C <br>
     * A -> D - > E - > A
     */
    public void testBranchGraph_KO( )
    {
        DetectCycleAlgo algo = DetectCycleGraphBuilder.builder( ).addToGraph( 4, 5 ).addToGraph( 1, 4 ).addToGraph( 5, 1 ).addToGraph( 2, 3 ).addToGraph( 1, 2 )
                .build( );

        assertTrue( algo.hasCycle( ) );
    }

    /**
     * A -> A
     */
    public void testSelfCycle( )
    {
        DetectCycleAlgo algo = DetectCycleGraphBuilder.builder( ).addToGraph( 1, 1 ).build( );

        assertTrue( algo.hasCycle( ) );
    }

    /**
     * C -> A -> B -> D
     */
    public void testNotOrderedGraph_OK( )
    {
        DetectCycleAlgo algo = DetectCycleGraphBuilder.builder( ).addToGraph( 2, 4 ).addToGraph( 3, 1 ).addToGraph( 1, 2 ).build( );

        assertFalse( algo.hasCycle( ) );
    }

    /**
     * C -> A -> B -> C <br>
     * C -> D
     */
    public void testNotOrderedGraph_KO( )
    {
        DetectCycleAlgo algo = DetectCycleGraphBuilder.builder( ).addToGraph( 2, 3 ).addToGraph( 1, 2 ).addToGraph( 3, 4 ).addToGraph( 3, 1 ).build( );

        assertTrue( algo.hasCycle( ) );
    }
}
